/*
* 개발: 정대규
* 최초: 2015.10.24
* 수정: 2017.01.05
* lisence: MIT
*/
"use strict";

/*
* lPad0(): 길이만큼 문자열 0을 채운다.
*/
Number.prototype.lPad0 = function(length){
	var result = "";
	for(var i = 0; i < length - this.toString().length; i++){
		result += "0";
	}
	return result + this;
} //end: Number.prototype.lPad0 = function(length){

/*
* Date의 날짜 형식을 unix timestamp 형식으로 변경한다.
*/
var timestamp = function(date){
	return date === undefined ? new Date().timestamp() : date.timestamp();
} //end: var timestamp = function(){

/*
* Date의 날짜 형식을 unix timestamp 형식으로 변경한다.
*/
Date.prototype.timestamp = function(){
	return Math.floor(this.getTime() / 1000);
} //end: Date.prototype.timestamp = function(){

var $DT = {
	local: {
		en: {
			weeks: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"]
			, months: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"]
			, noons: ["AM", "PM"]
		}
		, ko: {
			weeks: ["일", "월", "화", "수", "목", "금", "토"]
			, months: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"]
			, noons: ["오전", "오후"]
		}
	} //end: local: {

	/*
	* datefy(): 날짜를 format 형식에 맞게 출력
	*/
	, datefy: function(date, format, lang){
		format = format || "yyyymmddhhmissms";
		lang = lang || "ko";
		var local = this.local;

		return format.replace(/(yyyy|YY|mm|MM|dd|WW|hh|NN|HH|mi|ss|ms)/gi, function(pattern){
			switch(pattern){
				case "yyyy":
					return date.getFullYear();
				case "YY":
					return (date.getFullYear() % 1000).lPad0(2);
				case "mm":
					return (date.getMonth() + 1).lPad0(2);
				case "MM":
					return local[lang].months[date.getMonth()];
				case "WW":
					return local[lang].weeks[date.getDay()];
				case "dd":
					return date.getDate().lPad0(2);
				case "hh":
					return date.getHours().lPad0(2);
				case "NN":
					return local[lang].noons[date.getHours() < 12 ? 0 : 1];
				case "HH":
					var hour = 0;
					return ((hour = date.getHours() % 12) ? hour : 12).lPad0(2);
				case "mi":
					return date.getMinutes().lPad0(2);
				case "ss":
					return date.getSeconds().lPad0(2);
				case "ms":
					return date.getMilliseconds().lPad0(4);
			} //end: switch(pattern){
		}); //end: return format.replace(/(yyyy|YY|mm|MM|dd|WW|hh|NN|HH|mi|ss|ms)/gi, function(pattern){
	} //end: , datefy: function(date, format, lang){
	
	/*
	* theTime: timer가 종료될 시간을 입력받아야 한다.
	* tic: 매초마다 동작될 내용의 callback 함수
	* done: 남은 시간이 모두 끝나면 동작될 callback 함수
	*/
	, timer: function(theTime, data){
		var defaults = {
			theTime: theTime
			, interval: 1000
			, tic: function(){}
			, done: function(){}
		};
		$.extend(true, defaults, data);

		/*
		* timer가 종료될때까지 남은 시간을 계산한다.
		* 종료시간을 파라미터로 전달한다.
		* json 형태로 return하는 값은 다음과 같다.
		* gap: 종료까지 남은 전체 시간을 초로 넘긴다.
		* 아래의 dhms는 yyyymmdd와 같은 느낌은 남은 일수/시간/분/초를 넘긴다.
		* d: 종료까지 남은 일수
		* h: 종료까지 남은 시간
		* m: 종료까지 남은 분
		* s: 종료까지 남은 초
		* theTime: yyyy-mm-dd hh:mi:ss.mil
		*/
		var remain = function(theTime){
			var t = $.type(theTime) === "string" ? (Date.parse(theTime) - Date.parse(new Date())) / 1000 : (theTime - new Date()) / 1000;
			return {
				gap: t
				, d: Math.floor(t / 86400)
				, h: Math.floor(t / 3600 % 24)
				, m: Math.floor(t / 60 % 60)
				, s: Math.floor(t % 60)
			}
		}
	
		/*
		* interval을 통해 매초마다 동작되게 처리
		*/
		var interval = setInterval(function(){
			var t = remain(defaults.theTime); //남은 시간을 json 객체로 대입
			t.intervalId = interval; //json 객체에 interval이라는 key를 만들고 현재 interval의 ID를 대입
			if(t.gap < 0){ //남은 시간이 없으면
				clearInterval(interval); //interval 객체를 clear 처리
				defaults.done(); //입력받은 완료시 동작될 callback 함수를 실행
			}else{ //남은 시간이 있으면
				defaults.tic(t); //남은 시간의 json 값을 넘기고 매초마다 동작될 callback 함수를 실행
			}
		}, defaults.interval); //동작될 시간 지정
	} //end: timer: function(theTime, data){

	/*
	* 문자열/timestamp를 Date로 변환해서 처리
	* date: String/Number(default)/Date
	* num: 계산될 크기 지정
	* unit: "yyyy"/"mm"/"dd"(default)/"hh"/"mi"/"ss"
	* return: "yyyy-mm-dd hh:mi:ss"
	*/
	, calc: function(date, num, unit){
		var units = {
			ss: 1
			, mi: 60
			, hh: 3600
			, dd: 3600 * 24
			, mm: 3600 * 24 * 30
			, yyyy: 3600 * 24 * 365
		};

		/*
		* 입력받은 날짜를 timestamp로 변경한다.
		*/
		var theDate = 0;
		if(date === ""){
			theDate = new Date().timestamp();
		}else{
			switch($.type(date)){
				case "string": //yyyymmdd 포맷에 맞는 최소 문자열을 허용한다.
					theDate = parseString(date);

					var args = [];
					for(var i = 4; i <= 14; i += 2){
						args.push(i === 4 ? theDate.substr(0, 4) : theDate.substr(i - 2, 2));
					}

					theDate = new Date(args).timestamp();
				break;
				case "date":
					theDate = date.timestamp();
				break;
			}
		}

		theDate += (Number(num) * units[unit || "dd"]); //timestamp로 변경된 날짜를 계산

		return theDate.datefy("yyyy-mm-dd hh:mi:ss");
	}
}; //end: var $DT = {

/*
* String 날짜 형식을 format에 맞게 변경한다.
*/
String.prototype.datefy = function(format, lang){
	return $DT.datefy(new Date(Number(this) * 1000), format, lang);
}

/*
* unix timestamp 날짜 형식을 format에 맞게 변경한다.
*/
Number.prototype.datefy = function(format, lang){
	return $DT.datefy(new Date(this * 1000), format, lang);
}

/*
* Date 날짜 형식을 format에 맞게 변경한다.
*/
Date.prototype.datefy = function(format, lang){
	return $DT.datefy(this, format, lang);
}

/*
* 날짜 형식을 format에 맞게 변경한다.
*/
function datefy(date, format, lang){
	return $DT.datefy(date, format, lang);
}

/*
* String 시간을 받아서 종료전까지 콜백을 동작시킨다.
*/
String.prototype.timer = function(data){
	$DT.timer(this, data);
}

/*
* Date 시간을 받아서 종료전까지 콜백을 동작시킨다.
*/
Date.prototype.timer = function(data){
	$DT.timer(this, data);
}

/*
* 오늘 또는 첫번째 입력된 날짜부터 입력된 두번째 값만큼 계산된 날을 반환한다.
* 파라미터가 하나면 기준일은 오늘이 되고, 계산될 날의 크기로 대입됨.
*/
function addDate(){
	return arguments.length === 1 ? $DT.calc("", arguments[0]) : $DT.calc(arguments[0], arguments[1]);
}

/*
* 지정된 날짜를 입력된 수만큼 unit의 크기에 따라 증감시킨다.
* addDateTime() 직접 호출시 Number(timestamp)를 첫번째 파라미터로 넘길 수 있음.
* prototype시 Number는 객체로 인식되므로 참조할 수 없음.
*/
function addDateTime(date, num, unit){
	return $DT.calc(date, num, unit);
}

String.prototype.addDateTime = function(num, unit){
	return $DT.calc(this, num, unit);
}

Date.prototype.addDateTime = function(num, unit){
	return $DT.calc(this, num, unit);
}

/*
* 숫자를 제외한 모든 문자를 제거해서 반환한다.
*/
function parseString(str){
	return str.replace(/[^\d]+/g, "");
}

String.prototype.parseString = function(){
	return parseString(this);
}

/*
* yyyymmddhhmiss 형식의 문자열을 Date 객체로 반환한다.
* month는 문자열에서 -1로 처리해주어야 함.
* 년월일까지만 값이 들어오면 뒤는 0으로 처리함.
*/
function parseDate(str){
	return new Date(
		str.substr(0, 4)
		, "" + Number(str.substr(4, 2)) - 1
		, str.substr(6, 2)
		, str.length >= 10 ? str.substr(8, 2) : "00"
		, str.length >= 12 ? str.substr(10, 2) : "00"
		, str.length >= 14 ? str.substr(12, 2) : "00"
	);
}

String.prototype.parseDate = function(){
	return parseDate(this);
}
