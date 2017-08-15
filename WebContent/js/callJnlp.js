var agt = navigator.userAgent.toLowerCase();
var agtOS = navigator.userAgent.replace(/ /g,'');
var osCheak = false;
var browserCheak = false;
var webStartCheak = false;
var jnlpCheak = false;
var dFlag = 0;

$.os = {
	Linux: /linux/.test(agt),
	Unix: /x11/.test(agt),
	Mac: /mac/.test(agt),
	Windows: /win/.test(agt)
}

var callJnlp = function(userMasterKey, userKey, tab, lang, country){
	/*로컬*/
	//var hostAddr = "127.0.0.1:81";
	/*서버*/
	var hostAddr = "192.168.30.22:93";

	if($.os.Windows){
		osCheak = true;
		if (agt.indexOf("chrome") != -1 && agt.indexOf("opr") == -1) {
			//browserName= 'Chrome';
			browserCheak = true;
			dFlag = 1;
		} else if (agt.indexOf("safari") != -1 && agt.indexOf("opr") == -1) {
			//browserName= 'Safari';
			browserCheak = true;
			dFlag = 1;
		} else if (agt.indexOf("opr") != -1) {
			//browserName= 'Opera';
			browserCheak = true;
		}  else if (agt.indexOf("opera") != -1) {
			//browserName= 'Opera';
			browserCheak = true;
		} else if (agt.indexOf("firefox") != -1) {
			//browserName= 'Firefox';
			browserCheak = true;
			
			$(".result_footer .test_btn .icon").width('54px');
		} else if ((navigator.appName == 'Netscape' && navigator.userAgent.search('Trident') != -1) || (agt.indexOf("msie") != -1)) {
			var trident = navigator.userAgent.match(/Trident\/(\d.\d)/i);
			
			if(parseInt(agt.split('msie')[1]) == 7){
				//browserName = 'Internet Explorer 7';
				browserCheak = true;
			} else if(trident !=null && trident[1] == '7.0'){
				//browserName = 'Internet Explorer 11';
				browserCheak = true;
			} else if(trident !=null && trident[1] == '6.0'){
				//browserName = 'Internet Explorer 10';
				browserCheak = true;
			} else if(trident !=null && trident[1] == '5.0'){
				//browserName = 'Internet Explorer 9';
				browserCheak = true;
			} else if(trident !=null && trident[1] == '4.0'){
				//browserName = 'Internet Explorer 8';
				browserCheak = true;
			} else {
				browserCheak = false;
				alert("미지원 브라우저입니다.");
			}
		} else {
			browserCheak = false;
			alert("미지원 브라우저입니다.");
		}
	} else {
		osCheak = false;
		alert("미지원 OS입니다.");
	}
	
	if(osCheak && browserCheak) {
		if(typeof deployJava == 'undefined' || typeof deployJava == undefined){
			//자바 미지원
			webStartCheak = true;
			dFlag = 1;
		} else {
			pluginInfo = deployJava.getPlugin();
			pluginLength = deployJava.getJREs().length;
			
			if(pluginLength > 0) {
				if(pluginInfo == null){
					//자바 설치 확인불가
					webStartCheak = true;
					dFlag = 1;
				} else {
					//자바 설치 확인
					webStartCheak = true;
				}
			} else {
				if(agt.indexOf("chrome") > -1){
					//자바 설치 확인불가
					webStartCheak = true;
					dFlag = 1;
				} else {
					//자바 설치 안됨
					webStartCheak = false;
					alert("고객님 PC에 Java 프로그램이 설치 되어야 테스트를 시작하실 수 있습니다.\n확인 버튼을 클릭하시면 설치 파일이 다운로드 됩니다.\n※ 설치 완료 후 브라우저를 종료한 후 다시 열어 주시기 바랍니다.");
					location.href = "/upload/java/install.zip";
				}
			}
		}
	} else {
	}
	
	if(osCheak && browserCheak && webStartCheak){
		jnlpCheak = true;
	} else{
		jnlpCheak = false;
	}

	if(tab == undefined){
		tab = "";
	}
	
	if(jnlpCheak){
		//flag 파라미터 추가됨
		$.getJSON("//" + hostAddr + "/api/jnlpDownload.do?callback=?&id=" + userMasterKey + "&userKey=" + userKey + "&flag=" + dFlag + "&tab=" + tab+ "&lang=" + lang + "&country=" + country
		, function(data){
			if(data.result){
				if(dFlag == 1){ //사파리및 크롬은 파일 다운로드
					window.location.href = data.url;
				}else{//그외의 브라우저에서는 실행
					deployJava.launchWebStartApplication(data.url);
				}
			}else{
				alert("일시적으로 테스트가 불가능합니다.\n잠시후 다시 시작하시기 바랍니다.");
			}
		});
	}
};
