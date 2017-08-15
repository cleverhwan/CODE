﻿/*
*	2011.11.13 made by beans9(ahmax95@gmail.com)
*	http://beans9.tistory.com

	2013.04.21 
	- mask 처리
	- css 자동으로 입력되도록 수정
*/

var cssAddFlag = true;
function CalAddCss(){
		var cssHtml = "<style type='text/css'>";
		cssHtml += ".calLayout{position:absolute;display:none;z-index:9999	}</style>";			
		$("head").append(cssHtml);
}

// 초기화			
function initCal(obj){
	if( cssAddFlag == true ){
		CalAddCss();
		cssAddFlag = false;
	}
	
	// id 정의
	var calId = "Cal" + obj.id;				
	
	// calendar type (day,mon)
	var calType = obj.type == null ? "day" : obj.type;				
	if( calType.toLowerCase() != "day" && calType.toLowerCase() != "mon"){
		calType = "day";
	}
	
	// 최소년도, 최대년도 
	var minYear 	= obj.min == null ? "2000" : obj.min;
	var maxYear 	= obj.max == null ? new Date().getFullYear() : obj.max;
	var splitKey 	= obj.splitKey == null ? "-" : obj.splitKey; 
	var todayYN		= obj.today == null ? "n" : "y";
	var iconYN		= obj.icon  == null ? "n" : "y";
	var iconUrl    = obj.iconUrl == null ? "" : obj.iconUrl ; 

	// 달력 레이아웃(전체틀+월선택)그리기
	drawCalForm(calId,obj.id,calType,minYear,maxYear,todayYN,iconYN,iconUrl);
	
	if( calType == "day"){
		$("#"+obj.id).attr("maxlength",10);
		$("#"+calId).css("width","170px");
		$("#"+calId +"btn.todayBtn").css("width","157px");							
	}else{
		$("#"+obj.id).attr("maxlength",7);
		//$("#"+calId).css("width","146px");
		$("#"+calId +"btn.todayBtn").css("width","133px");
	}

	
	// input box에 click event 
	$("#"+obj.id + ",#"+ calId + "Icon").click(function(event){					
		var datesVal = $("#"+obj.id).val();
		if( calType == "mon") datesVal += splitKey + '1';
		
		var dates = isValidCheck(datesVal ,splitKey);
		if( calType == "day"){
			makeCal(calId,dates,splitKey);
		}else{
			makeCalMon(calId,dates,splitKey);
		}
		calPosition(obj.id,calId);																					
		$("#"+calId).show();
		
	});
	
	// 외부 화면 클릭시 달력감추기
	$(document).click(function(event){
		var parentEls = $( event.target).parents().map(function () { return this.id; }).get().join(", ");
		if(parentEls.indexOf(calId+",") == -1 && event.target.id != obj.id && parentEls.indexOf(calId+"Icon,")){
			$("#"+calId).hide();
		}
	});
	
	$(window).resize(function(event){
		calPosition(obj.id,calId);
	});
	
	// 달력상단 이전/다음 버튼 처리
	$("#"+calId+"before,#"+calId+"next").click(function(event){
		var plus = -1;
		if(this.id.indexOf("next") > -1){ var plus = 1; }
		
		if( calType == "day"){
			var dates = calDate($("#"+ calId + "selYear").val(),$("#"+calId + "selMon").val(),plus,isValidCheck($("#"+obj.id).val(),splitKey));					
			if((dates[0] >= minYear && plus == -1) || (dates[0] <= maxYear && plus == 1)){
				makeCal(calId,dates,splitKey);
			}	
		}else{
			var dates = calDate($("#"+ calId + "selYear").val(),'1',plus*12,isValidCheck($("#"+obj.id).val()+splitKey+'1',splitKey));
			if((dates[0] >= minYear && plus == -1) || (dates[0] <= maxYear && plus == 1)){
				makeCalMon(calId,dates,splitKey);
			}
		}					 
	}).mouseover(function(){
		$(this).addClass("divHeadOnRigth");
	}).mouseout(function(){
		$(this).removeClass("divHeadOnRigth");
	});;
	
	// 월별선택시 필요없음
	if( calType == "day"){
		// 년도,월 selectbox 선택시 이벤트
		$("#"+calId+"selYear,#"+calId+"selMon").change(function(event){
			var dates = calDate($("#"+ calId + "selYear").val(),$("#"+calId + "selMon").val(),0,isValidCheck($("#"+obj.id).val(),splitKey));					
			makeCal(calId,dates,splitKey);
		});	
		$("#"+obj.id).mask('1111'+ splitKey +'11'+ splitKey +'11');
	}else{
		$("#"+calId+"selYear").change(function(event){
			var dates = calDate($("#"+ calId + "selYear").val(),1,0,isValidCheck($("#"+obj.id).val()+splitKey+'1',splitKey));					
			makeCalMon(calId,dates,splitKey);
		});	
		$("#"+obj.id).mask('1111'+ splitKey +'11');
	}
	
	$("#"+calId+"btn").click(function(){
		$("#"+obj.id).val("");
		var dates = isValidCheck('',splitKey);
		if( calType == 'day'){
			makeCal(calId,dates,splitKey);			
		}else{
			makeCalMon(calId,dates,splitKey);		
		}
	});
	
	calPosition(obj.id,calId);
}

//클릭 이벤트
function calClickEvent(id){
	$("#" + id + " .calOtherDay,.calDay,.calSunDay,.calSatDay,.MonToday,.selMon,.dayEvMon").click(function(event) {
        $("#" + id.substring(3)).val(this.id.replace(id,""));
		$("#" + id).hide();
	}).mouseover(function(){
		$(this).addClass("calOnDay");
	}).mouseout(function(){
		$(this).removeClass("calOnDay");
	});
} 

// 달력 폼 그리기 
function drawCalForm(id,tId,calType,min,max,todayYN,iconYN,imageUrl){ // tId = 입력창ID

	if(iconYN == "y"){
		//$(document.body).append("<div id='"+ id +"Icon' style='position:absolute'><img id='"+ id +"IconImg' src='"+ imageUrl +"'><div>");
		//$(document.body).append("<div id='"+ id +"Icon' class='calBtn' style='position:absolute'><button type='button' id='"+ id +"IconImg' class=calIcon'>월선택</button><div>");
		$("#"+tId).parent().append("<div id='"+ id +"Icon' class='calBtn'><button type='button' id='"+ id +"IconImg' class=calIcon'>월선택</button><div>");
	}
	
	// layout
	$(document.body).append("<div id='"+ id +"' onselectstart='return false' class='calLayout'><div>");
	
	// 상단 년,달 이동 
	var html  = "<div class='calTitle'><a href='#' id='"+ id +"before' class='prev'>이전</a>";
		
	// 년선택
	html += "<select id='"+ id +"selYear'>";
    for(var i=min;i<=max;i++){ html += "<option value=" + i + ">" + i + "</option>"; }
	html += "</select>"
	
	// 월선택 (일별달력일 경우에만)
	if( calType == "day"){
		html += "<select id='"+ id +"selMon'>";
	    for(var i=1;i<=12;i++){ html += "<option value=" + i + ">" + i + "월</option>"; }
		html += "</select>"						
	}
	
	html += "<a href='#' id='"+ id +"next' class='next'>다음</a></div>";
	if( calType == "day" )html += "<div id='" + id + "Header' class='calHeader'></div>";
	html += "<div id='" + id + "Content' class='calBody'></div>";
	
		
	$("#"+id).html(html);				
}

// 날짜변경(달변경) 달력내용 그리기
function makeCal(id,dates,splitKey){				
	
	// 이번달 시작날짜,마지막날짜 
	var firstDay  = new Date(dates[0],eval(dates[1]-1),1).getDay();
	
	// 이번달 마지막날짜
	var lastDate 	= new Date(dates[0],eval(dates[1]),0);
	var lastDateNum = lastDate.getDate();
	var lastDay     = lastDate.getDay();
	
	// 이전달 마지막날짜
	var beforeMonDate	= new Date(dates[0],eval(dates[1]-1),0);
	var beforeMonLastDay = beforeMonDate.getDate();
	var beforeYearMonText = beforeMonDate.getFullYear() + splitKey + strFormat(beforeMonDate.getMonth()+1) + splitKey;
	
	var nextMonDate	= new Date(dates[0],eval(dates[1]),1);
	var nextYearMonText = nextMonDate.getFullYear() + splitKey + strFormat(nextMonDate.getMonth()+1) + splitKey;
	  
	var nowYearMonText = dates[0] + splitKey + strFormat(dates[1]) + splitKey;
	
	var ndate = new Date();
	var today = ndate.getFullYear() + splitKey + strFormat(ndate.getMonth()+1) + splitKey + strFormat(ndate.getDate());
	//-----------------------------------------------------	
	var html = "";				
	var brCount = 1; // 줄넘김 count
	
	// before month
	for( i = 1; i<=firstDay; i++){
		html += "<div class='calOtherDay' id='"+ id + beforeYearMonText + (beforeMonLastDay-(firstDay-i)) +"'>"+ (beforeMonLastDay-(firstDay-i)) + "</div><div class='calDaySpace'></div>";
		brCount ++;					
	}	
					
	// now month
	for (i = 1; i <= lastDateNum; i++) {					
		var dayCss = "calDay";				
		if (brCount % 7 == 1) { dayCss = "calSunDay" ; }
		else if(brCount % 7 == 0){ dayCss = "calSatDay" ; }
	
		html += "<div class='"+ dayCss +"' id='"+ id + nowYearMonText + strFormat(i) +"'>" + i + "</div>";
		
		if( brCount %7 == 0 && i != lastDateNum ){ html += "<div class='calDayBr'></div>"; }
		else{ html += "<div class='calDaySpace'></div>"; }
		brCount ++;
	}
	
	// next month
	var nexyStartDay = 1;
	for( i = lastDay; i<6; i++){
		html += "<div class='calOtherDay' id='" +id + nextYearMonText + strFormat(nexyStartDay) +"'>"+ nexyStartDay++ + "</div><div class='calDaySpace'></div>";
	}
	
	// 내용 삽입
	$("#" + id + "Content").html(html);
	
	// 년도 select 설정
	$("#" + id + "selYear").val(dates[0]);
	
	// 일별달력일 경우 월 select 선정
	$("#" + id + "selMon").val(eval(dates[1]));
	
	// 오늘날짜 체크
	$("#" + id+ today).addClass("dayToday");
	
	// 입력날짜 체크
	if(dates[4] == true){
		$("#" +id + dates[3]).addClass("calSelDay");					
		// 입력날짜가 유효한 날짜면 input쪽 데이터를 형식에 맞게 변경
		$("#" + id.substring(3)).val(dates[3]);
	} 
	
	calClickEvent(id);
}

function makeCalMon(id,dates,splitKey){
	var nowYeaText = dates[0] + splitKey;
	
	var ndate = new Date();
	var today = ndate.getFullYear() + splitKey + strFormat(ndate.getMonth()+1);
	
	var html = "<div class='calDayBr'></div><div class='calDayBr'></div>";				
	for(var i=1;i<13;i++){
		html += "<div class='dayEvMon' id='"+ id + nowYeaText + strFormat(i) +"'>" + i + "월</div>";
		if( i %4 == 0 && i != 12){ html += "<div class='calDayBr'></div>"; }
		else{
			html += "<div class='calDaySpace'></div>"; 
		}
	}				
	// 내용 삽입
	$("#" + id + "Content").html(html);				
	// 년도 select 설정
	$("#" + id + "selYear").val(dates[0]);
	
	// 오늘날짜 체크
	$("#" + id + today).addClass("MonToday");
	
	// 입력날짜 체크
	if(dates[4] == true){
		$("#" +id + dates[3].substring(0,7)).addClass("selMon");
		
		// 입력날짜가 유효한 날짜면 input쪽 데이터를 형식에 맞게 변경
		$("#" + id.substring(3)).val( dates[3].substring(0,7));
	} 
	
	calClickEvent(id);
}

// 날짜 유효성 체크
function isValidCheck(strDate,splitKey,todayCheck){
	var date = strDate;
	
	var dates = date.split(splitKey);
	var idate = new Date(dates[0],eval(dates[1])-1,eval(dates[2]));
	var ndate = new Date();
	
	var isValid = true;
	if( dates[0] != idate.getFullYear()) isValid = false;  
	else if( eval(dates[1])-1 != idate.getMonth()) isValid = false; 
	else if( eval(dates[2]) != idate.getDate()) isValid = false;

	if( isValid == false ){
		dates[0] = ndate.getFullYear();
		dates[1] = strFormat(ndate.getMonth()+1);
		dates[2] = strFormat(ndate.getDate());
	}	
	
	dates[3] = dates[0] +splitKey + strFormat(dates[1]) + splitKey + strFormat(dates[2]);
	dates[4] = isValid;
	return dates;
}	

function calDate(year,month,adjust,isValidDate){
	var date = new Date(year,(month-1)+adjust,1);
	var dates = [date.getFullYear(),date.getMonth()+1,date.getDate(),isValidDate[3],isValidDate[4]];
	return dates;
}

// 날짜 format 맞추기
function strFormat(str){
	if((str+"").length == 1) str = "0" + str;
	return str;				
}

// 달력위치변경		
function calPosition(target,id){
	var tInput  = $("#" + target).offset();
	var tHeight = $("#" + target).outerHeight();
	var tWidth 	= $("#" + target).outerWidth();
	var calHeight 	= $("#" + id).outerHeight();
	console.log(tInput, tWidth);
	
	if( tInput != null){
		$("#" + id).css({"top":tInput.top+tHeight , "left":tInput.left});
		$("#" + id + "Icon").css({"top":tInput.top,"left":tInput.left+tWidth});
	}
}