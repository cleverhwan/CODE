function hasJqueryObject( elem ){return elem.length > 0;}

var app = {};
//window.onload=function(){ init(); };

function init()
{
	app.$body = $("body");
	if( hasJqueryObject( app.$body.find(".select") ) ) initZelect();
	if( hasJqueryObject( app.$body.find(".input-text") ) ) initInput();
}

/* select 스타일 */
function initZelect(){
	var zSelect = $(".select");
	var count = zSelect.length;

	for(var i=0; i<count; i++){
		/* select에 js 적용을 위한 class 추가 */
		var num = i+1;
		zSelect.eq(i).addClass("set"+num);
		$(".select.set"+num).zelect();

		/* select가 disabled 일때 */
		if(zSelect.eq(i).attr("disabled")==("disabled")){
			zSelect.eq(i).next().addClass("disabled");
		}
	}
}

/* input placeholder*/
function initInput(){ $('input, textarea').placeholder(); }



/* gnb */
$(document).ready(function(){
//	$('#container').before('<div class="fixed-top"></div>');
	$('.depth2').append('<div class="ic-triangle"></div>');
	$('#gnb > ul > li > a, .top-box > ul > li > a').keydown(function(e){
		if(e.keyCode === 13) { // enter
			$(this).next('.depth2').each(function(index, el) {
				e.preventDefault();
				$(this).parent().addClass('on');
				$(this).css('display','block');
				$(this).next().find('ul > li:first-child > a').focus();
			});
		}
	});

	$('#gnb > ul > li, .top-box > ul > li > a').mouseenter(function(e) {
		$(this).addClass('on');
		$(this).find('.depth2').css('display','block');
	}).mouseleave(function(e) {
		$(this).removeClass('on');
		$(this).find('.depth2').css('display','none');
	});

	$('.depth2 > ul > li:first-child > a').keydown(function(e){
		if((e.keyCode === 9 && e.shiftKey)){ // shift+tab
			e.preventDefault();
			$(this).closest('.depth2').prev().focus();
			$(this).parents('li').removeClass('on');
			$(this).parents('.depth2').css('display','none');
		}
	});

	$('.depth2 > ul > li:last-child > a').keydown(function(e){
		if(e.keyCode === 9 && !e.shiftKey) { // enter
			e.preventDefault();
			$(this).closest('.depth2').parent().next().find('a').focus();
			$(this).parents('li').removeClass('on');
			$(this).parents('.depth2').css('display','none');
		}
	});

	//footer
	$('#go-top').click(function(e){
		e.preventDefault();
		$('html, body').animate({'scrollTop':0}, 500);
	});




});


// 타이틀영역에 탭이 있을경우 [탭클래스 체크해서 고정영역 생성되는 클래스 추가되며 동작] -----  위치값은 #id 이동으로 처리되어있음(css처리) 각페이지마다 이동되는 위치가 바껴서 
$(document).ready(function(){
	if ($(".type-a").length > 0) {
		scrollMotion();
		$(window).scroll(function () {
			scrollMotion();
		});

		function scrollMotion(){
			if ($(document).scrollTop() > 222) {
				if( $('.opentablist-wrap').css("display") == "none" ){
					$('.opentablist-wrap').show().stop(true).animate({ top : "0" }, 500);
				}
			} else {
				if( $('.opentablist-wrap').css("display") != "none" ){
					$('.opentablist-wrap').stop(true).animate({ top: "-68" }, 10, function(){
						$(this).hide();
					});
				}
			}
		}
		var $tabMenuLink = $('.tab-list li > a');
		var $tabMenuli = $('.opentablist-wrap .inner > .tab-list li').length;
		$tabMenuLink.on("click", function (){
			var index = $tabMenuLink.index(this);

			$tabMenuLink.parent().removeClass('current');

			$(this).parent().addClass('current');
			if( index < $tabMenuli ){
				$tabMenuLink.eq(index + $tabMenuli).parent().addClass('current');
			} else {
				$tabMenuLink.eq(index - $tabMenuli).parent().addClass('current');
			}
		});
		$(window).scroll(function () {
			var scLocation = $(document).scrollTop();
			//체험유료상품
			if (scLocation > 0 && scLocation < 776 ) {$('.tablist01').trigger('click');} 
			else if (scLocation > 777 && scLocation < 1329 ) {$('.tablist02').trigger('click');}
			else if (scLocation > 1330 && scLocation < 1615 ) {$('.tablist03').trigger('click');}
			else if (scLocation > 1616 && scLocation < 1935 ) {$('.tablist04').trigger('click');}
			else if (scLocation > 1936 && scLocation < 2000 ) {$('.tablist05').trigger('click');};
			//1Q MTCenter 소개
			if (scLocation > 0 && scLocation < 3457 ) {$('.introduce01').trigger('click');} 
			else if (scLocation > 3458 && scLocation < 4416 ) {$('.introduce02').trigger('click');};
			//on-Premise
			if (scLocation > 0 && scLocation < 999 ) {$('.onpremise01').trigger('click');} 
			else if (scLocation > 1000 && scLocation < 1890 ) {$('.onpremise02').trigger('click');}
			else if (scLocation > 1891 && scLocation < 2800 ) {$('.onpremise03').trigger('click');};
			//자동화 테스트
			if (scLocation > 0 && scLocation < 716 ) {$('.intest01').trigger('click');} 
			else if (scLocation > 717 && scLocation < 1600 ) {$('.intest02').trigger('click');}
			else if (scLocation > 1601 && scLocation < 3400 ) {$('.intest03').trigger('click');};
		});

	}

});




// 레이어 팝업 플러그인 네이밍
var pluginName = 'modal_popup';

// Init modal_popup 객체 생성자
function modal_popup($selector, options) {
  this.$selector = $selector;
  this.config = $.extend({}, this.defaults, options || {});
  
  this.detect = {
    $doc : $(document),
    $win : $(window),
    body : $('body')
  }
  
  this._init(this.detect);
}

modal_popup.prototype = {
  'defaults' : {
    btnClass : 'layer-popup',
    modalClose : 'btnp-close',
    fade : false,
    fadeSpeed : 400,
    dimmed : true,
    dimOpacity : .8,
    escKey : false,
    clickDimmed : false
  },
  '_init' : function (detect) {
    this._setEvent(detect)
  },
  '_setEvent' : function (detect) {
    var _self = this;
    if(detect.$doc.data('layerEvent')) {return;}
    $(document).data('layerEvent', true);
    
    detect.$doc.on('click', '.' + this.config.btnClass, function (e) {
	event.stopPropagation();
		_self.$target = $(e.target);
		_self.href = _self.$target.attr('href');
		_self.$modalContent = $(_self.href);
		_self._setProp(_self.$modalContent, detect);
		_self._lockScroll(_self.detect);
		_self._checkESC(_self.detect);
		_self._clickDimmed();
		return false;
    })
  },
  '_setProp' : function($modalContent, detect) {
    this.modalWidth = $modalContent.outerWidth();
    this.modalHeight = $modalContent.outerHeight();
    //this.winWidth = detect.$win.width();
    this.winHeight = detect.$win.height();
    this.widthHalf = (this.modalWidth / 2);
    this.heightHalf = (this.modalHeight / 2);
    this.modalClose = $modalContent.find('.' + this.config.modalClose);
    
    if( this.config.dimmed ) this._setDim();
    
    if( this.modalHeight < this.winHeight ) {
      this.$modalContent
          .css({
              marginTop : -this.heightHalf,
              marginLeft : -this.widthHalf
          })
    } else {
      this.$modalContent
          .css({
              top : 50,
              marginLeft : -this.widthHalf
          })
    }
    
    this.config.fade ? this._modalFadeIn() : this._modalShow();
    this._modalClose(this.modalClose);
    
  },
  '_modalShow' : function () {
    this.$modalContent.show();
    this._dimShow();
    this._focusIn();
  },
  '_modalHide' : function () {
    this.$modalContent.hide();
    this._dimHide();
  },
  '_modalFadeIn' : function () {
    this.$modalContent.fadeIn(this.config.fadeSpeed);
    this._dimFadeIn();
    this._focusIn();
  },
  '_modalFadeOut' : function () {
    this.$modalContent.fadeOut(this.config.fadeSpeed);
    this._dimFadeOut();
  },
  '_modalClose' : function (modalClose) {
    var _self = this;
    modalClose.on('click', function(e) {
      _self._unlockScroll(_self.detect);
      _self.config.fade ? _self._modalFadeOut() : _self._modalHide();
      
      if ( _self.config.dimmed) _self.$dimLayer.remove();
      
      _self._focusOut();
      
      return false;
    })
  },
  '_focusIn' : function () {
    this.$modalContent
        .attr('tabindex', 0)
        .focus();
  },
  '_focusOut' : function () {
    this.$target
        .attr('tabindex', 0)
        .focus();
    this.$modalContent.attr('tabindex', -1)
  },
  '_setDim' : function () {
    if(this.config.dimmed) {
      this.detect.body
          .append('<div class="dim-layer"></div>');
      this.$dimLayer = $('.dim-layer');
      this.dimLayerConfig = {
        'display' : 'none',
        'position' : 'fixed',
        'left' : 0,
        'right' : 0,
        'top' : 0,
        'bottom' : 0,
        'background' : '#000',
        'width' : '100%',
        'height' : '100%',
        'z-index' : 100
      };
      // this.dimBgConfig = {
      //   'position' : 'absolute',
      //   'top' : 0,
      //   'left' : 0,
      //   'width' : '100%',
      //   'height' : '100%',
      //   'background' : '#000'
      // }
      this.$dimLayer
          .fadeIn()
          .css(this.dimLayerConfig)
          // .find('.dim-bg')
          // .css(this.dimBgConfig)
          .css({opacity : this.config.dimOpacity});
    }
  },
  '_dimFadeIn' : function () {
    this.config.dimmed && this.$dimLayer.fadeIn();
  },
  '_dimFadeOut' : function () {
    this.config.dimmed && this.$dimLayer.fadeOut();
  },
  '_dimShow' : function () {
    this.config.dimmed && this.$dimLayer.show();
  },
  '_dimHide' : function () {
    this.config.dimmed && this.$dimLayer.hide();
  },
  '_lockScroll' : function (detect) {
    detect.body.addClass('layer-scroll');
  },
  '_unlockScroll' : function (detect) {
    detect.body.removeClass('layer-scroll');
  },
  '_checkESC' : function (detect) {
    var _self = this;
    if(this.config.escKey) {
      detect.$doc.on('keydown', function (e) {
        if(e.whick == 27) {
          _self.$modalContent.hide();
          _self.$dimLayer.remove();
          _self._focusOut();
        }
      })
    }
  },
  '_clickDimmed' : function () {
    var _self = this;
    if(this.config.clickDimmed) {
      this.$dimLayer.on('click', function(e) {
        _self.$modalContent.hide();
        _self.$dimLayer.remove();
        _self._focusOut();
        return false;
      })
    }
  }
  
}

$.fn[pluginName] = $.fn[pluginName] || function(options) {
  var $this = this;

  return $.each($this, function (idx, el) {
    var $element = $this.eq(idx);
    if ( !$.data(this, 'plugin_' + pluginName)) {
          $.data(this, 'plugin_' + pluginName, new modal_popup($element, options))
    }
    return $element;
  })
}




//qc 파일 다운로드.
var getAttachFileDownLoad = function(attachType,attachId,attachNo){
	var downloadFormStr = "";
	downloadFormStr += "<form id = \"fileDownLoadForm\" name = \"fileDownLoadForm\" method=\"post\">";
	downloadFormStr += "<input type=\"text\" id=\"attach_type\"  name=\"attach_type\" value=\""+attachType+"\">";
	downloadFormStr += "<input type=\"text\" id=\"attach_id\"  name=\"attach_id\" value=\""+attachId+"\">";
	downloadFormStr += "<input type=\"text\" id=\"attach_no\"  name=\"attach_no\" value=\""+attachNo+"\">";
	downloadFormStr += "</form>";
	
	$('body').append(downloadFormStr);
	
	$( "#fileDownLoadForm" ).attr("action", "/qc/comm/getAttachFileDownload.do");
	$( "#fileDownLoadForm" ).attr("target", "_self");
	$( "#fileDownLoadForm" ).submit();
	$( "#fileDownLoadForm" ).remove();
}


// qc paging set
/**
 * cls : 표현하고자 하는 페이징 class 안주면 .paging
 * count : 전체건수 안주면 0
 * pagenum : 현재페이지번호  안주면 1
 * pageSize : 한페이지 list갯수 기본 안주면 (10)
 * pageBlock : 한화면에 보여지는 페이지 번호갯수 기본 안주면 (10)
 */
function pagingListSet(cls,count,pageNum,pageSize,pageBlock){
	if(cls == null || cls == "") cls = ".paging";
	var isPrevPage = false;
	var isNextPage = false;
	var totalPage = 0;
	var startRow = 1;
	var endRow = 10;
	var startPage = 1;
	var endPage = 10;
	
	totalPage = Math.ceil(count / pageSize); // 총 데이터 개수 / 한 페이지 당 리스트 개수 = 총 데이터 개수가 30개고 한 페이지당 리스트 개수가 15개면 2페이지가 나온다
	startRow=(pageNum-1) * pageSize+1; // 시작 열이 몇개부터 계산 되는지 만약 2페이지이고 PageSize가 15면 (2-1) * 15 + 1 = 1 * 16 = 16 ~ 30 까지가 된다
	endRow = pageNum * pageSize; // 위에서 16부터면 끝 열을 계산한다
	
	//startPage=((pageNum-1)/pageBlock) * pageBlock+1; // ((2-1)/10) * 10 + 1 = 10페이지 넘으면 11페이지로 보여주는게 startPage
	startPage = (Math.floor((pageNum-1)/pageBlock) * pageBlock) + 1;
	//endPage = startPage + pageBlock -1; //startPage가 11이면 11~20까지 보여주는게 endPage
	endPage = (startPage + pageBlock) -1;
	if(endPage>totalPage){ // 전체 페이지수보다 endPage 개수가 많다면 전체 페이지수를 endPage에 대입시킨다
	   endPage = totalPage;
	}
	
	if(startPage>1){ //startPage가 1보다 크면 isPrevPage를 true로 저장시킨다
	   isPrevPage = true;
	}
	
	if(endPage < totalPage){ // endPage가 totalPage수보다 작으면 isNextPage를 ture로 저장시킨다 , 즉 11~20 인데 전체 페이지수가 20페이지보다 크면 NextPage를 true
	   isNextPage = true;
	}
	
	var pageListValue = "";
	
	
	if(count > 0){ //건수가 있어야 페이지도 보여줌
		pageListValue = "";
			if(startPage <= pageBlock) { // startPage가 PageBlock보다 작으면
				//pageListValue += "<li><a href=\"javascript:void(0)\" onclick=\"return false;\">&lt;&lt;</a></li>";
				//pageListValue += "<li><a href=\"javascript:void(0)\" onclick=\"return false;\">&lt;</a></li>";
			}else{
				if(cls==".popupPaging"){
					pageListValue += "<li><a href=\"javascript:void(0);\" onclick=\"javascript:goPage2(1)\">&lt;&lt;</a></li>";
					pageListValue += "<li><a href=\"javascript:void(0);\" onclick=\"javascript:goPage2("+ (startPage -1) +")\">&lt;</a></li>";
				}else{
					pageListValue += "<li><a href=\"javascript:void(0);\" onclick=\"javascript:goPage(1)\">&lt;&lt;</a></li>";
					pageListValue += "<li><a href=\"javascript:void(0);\" onclick=\"javascript:goPage("+ (startPage -1) +")\">&lt;</a></li>";
				}
			}
	
			
			for(i=startPage;i<=endPage;i++){ //한화면에 보여지는 페이지 갯수.
				if(i == pageNum){
					pageListValue += "<li class=\"selected\"><span>"+i+"</span></li>";
				}else{
					if(cls==".popupPaging"){
						pageListValue += "<li><a href=\"javascript:void(0)\" onclick=\"javascript:goPage2("+i+")\">"+i+"</a></li>";
					}else{
						pageListValue += "<li><a href=\"javascript:void(0)\" onclick=\"javascript:goPage("+i+")\">"+i+"</a></li>";
					}
				}
			}
			
			 if(endPage<totalPage){   // endPage가 totalPage 보다 작다면 Next 버튼 활성화!!
				 if(cls==".popupPaging"){
					 pageListValue += "<li><a href=\"javascript:void(0);\" onclick=\"javascript:goPage2("+ (endPage +1) +")\">&gt;</a></li>";
				 }else{
					 pageListValue += "<li><a href=\"javascript:void(0);\" onclick=\"javascript:goPage("+ (endPage +1) +")\">&gt;</a></li>";
				 }
			 }else if(endPage == totalPage){
				 //pageListValue += "<li><a href=\"javascript:void(0)\" onclick=\"return false;\">&gt;</a></li>";
			 }else{
				 if(cls==".popupPaging"){
					 pageListValue += "<li><a href=\"javascript:void(0);\" onclick=\"javascript:goPage2("+ endPage +")\">&gt;</a></li>";
				 }else{
					 pageListValue += "<li><a href=\"javascript:void(0);\" onclick=\"javascript:goPage("+ endPage +")\">&gt;</a></li>";
				 }
			 }
			 if ( totalPage != pageNum && endPage < totalPage ){
				 if(cls==".popupPaging"){
					 pageListValue += "<li><a href=\"javascript:void(0);\" onclick=\"javascript:goPage2("+ totalPage +")\">&gt;&gt;</a></li>";
				 }else{
					 pageListValue += "<li><a href=\"javascript:void(0);\" onclick=\"javascript:goPage("+ totalPage +")\">&gt;&gt;</a></li>";
				 }
			 }else{
				 //pageListValue += "<li><a href=\"javascript:void(0)\" onclick=\"return false;\">&gt;&gt;</a></li>";
			 }
		
		$(cls + ' > ul').html(pageListValue); //페이지 만들고 입력.
	} else {
		$(cls + ' > ul').html( "<li class=\"selected\"><span>1</span></li>" ); // 1페이지만 생성
	}
}


//qc 공통코드 셋.
/**
 * type : codegroup
 * selectId : 입력하고자 하는 select id
 * val : 선택하고자 하는 값
 * opt : 옵션. 1:전체,2:선택,3:텍스트없음
 */
var fn_commonCodeSet = function(type,selectId,val,opt,rIdx){
	$.ajax({
		url: "/qc/comm/getCommonCodeList.do",
		type: "POST",
		dataType : "json",
		async : false,
		data:  {"type":type},
		success: function(data) {
			if(data.resultList != null && data.resultList.length > 0) {
				var optTxt = "";
				if(opt == "1") {optTxt = "<option value=''>전체</option>"}
				else if(opt == "2") {optTxt = "<option value=''>선택</option>"}
				else if(opt == "99"){optTxt="";}
				else {optTxt = "<option value=''></option>"}
				
				var selectHtml = optTxt;
				$.each(data.resultList,function (idx,item){
					if(item.code == val){
						selectHtml += "<option value='"+item.code+"' selected>"+item.value+"</option>";
					}else{
						selectHtml += "<option value='"+item.code+"'>"+item.value+"</option>";
					}
				});
				
				var arrIdx = 0;
				if(rIdx == "" || rIdx == 'undefined' || rIdx == undefined) {
					arrIdx = 0;
				}else{
					arrIdx = rIdx;
				}
				if($("select[name^="+selectId).length > 0){
					$("select[name^="+selectId).eq(arrIdx).html(selectHtml);
				}
				
				
			} else {
				alert(type + " 에 해당하는 공통코드가 존재하지 않습니다.\n\n확인해 주시기 바랍니다.");
			}
		},
		error: function() {
			alert("알수 없는 오류 입니다.");
		}
	});
}


//qc 공통코드 셋.
/**
 * type : codegroup
 * selectId : 입력하고자 하는 select id
 * val : 선택하고자 하는 값
 * opt : 옵션. 1:전체,2:선택,3:텍스트없음
 */
var fn_categoryCodeSet = function(url,sdata,selectId,val,opt,rIdx){
	var arrIdx = 0;
	if(rIdx == "" || rIdx == 'undefined' || rIdx == undefined) {
		arrIdx = 0;
	}else{
		arrIdx = rIdx;
	}
	var optTxt = "";
	if(opt == "1") {optTxt = "<option value=''>전체</option>"}
	else if(opt == "2") {optTxt = "<option value=''>선택</option>"}
	
	if(sdata.upcode != "" && sdata.upcode !=null){
		$.ajax({
			url: url,
			type: "POST",
			dataType : "json",
			async : false,
			data:  {"upcode":sdata.upcode,"paramAppId":sdata.paramAppId,"paramCompanyCode":sdata.paramCompanyCode},
			success: function(data) {
				
				if(data.resultList != null && data.resultList.length > 0) {
					var selectHtml = optTxt;
					$.each(data.resultList,function (idx,item){
						if(item.group_id == val){
							selectHtml += "<option value='"+item.group_id+"' selected>"+item.group_id+"</option>";
						}else{
							selectHtml += "<option value='"+item.group_id+"'>"+item.group_id+"</option>";
						}
					});
					
					if($("select[name^="+selectId).length > 0){
						$("select[name^="+selectId).eq(arrIdx).html(selectHtml);
					}
				}else{
					if($("select[name^="+selectId).length > 0){
						$("select[name^="+selectId).eq(arrIdx).html(optTxt);
					}
				}
			},
			error: function() {
				alert("알수 없는 오류 입니다.");
			}
		});
	}else{
		if($("select[name^="+selectId).length > 0){
			$("select[name^="+selectId).eq(arrIdx).html(optTxt);
		}
	}
}

//날자 비교 - 시작과 종료일자 비교해서 체크
var checkDateStartEnd = function(startDate,endDate){
	var rtn = true;
	var startArray = startDate.split("-");
	var endArray = endDate.split("-");
	var start_date = new Date(startArray[0],startArray[1],startArray[2]);
	var end_date = new Date(endArray[0],endArray[1],endArray[2]);
	if(start_date > end_date) {
		rtn = false;
	}else{
		rtn = true;
	}
	return rtn;
}


////롤별 버튼 리스트 가져오기.
//var getRoleByButtonList = function(srcode,pheader){
//	$.ajax({
//		url: "/qc/comm/getCommRoleByButtonList.do",
//		type: "POST",
//		dataType : "json",
//		async : true,
//		data : {"srcode":srcode,"pheader":pheader},
//		success: function(data) {
//			if(data.sessionYn =="Y"){
//				if(data.resultList != null && data.resultList.length > 0) {
//					return data;
//				}
//			}else{
//				alert("세션이 만료되었습니다.\n\n재 로그인 하시기 바랍니다.");
//			}	
//		},
//		error: function() {
//			alert("알수 없는 오류 입니다.");
//		}
//	});
//}