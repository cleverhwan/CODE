//반드시 jquery 뒤에 해당 파일을 위치시켜야함.
"use strict";

/*
* $.put, $.delete로 ajax 동작시킬 수 있음. restful api를 쉽게 이용하기 위함.
*/
$.each(["put", "delete"], function(i, method){
	$[method] = function(url, data, callback, type){
		if($.isFunction(data)){
			type = type || callback;
			callback = data;
			data = undefined;
		}

		return $.ajax({url: url, type: method, dataType: type, data: data, success: callback});
	}
});

/*
* input, textarea, select가 readonly나 disabled 상태인데 backspace를 누르면 뒤로가기가 되는 현상을 막기 위한 처리
*/
$(document).on("keydown", function(e){
	if(e.keyCode === 8){ //backspace key
		var $this = $(e.target);
		var status = $this.attr("readonly") !== undefined || $this.attr("disabled") !== undefined;
		if(["INPUT", "TEXTAREA", "SELECT"].indexOf($this.prop("tagName")) !== -1 && status){
			return false;
		}
	}
});

/*
* 문서 내에서 z-index가 가장 높은 값 + 1을 반환
*/
var zIndex = function(){
	var MAX = 2147483647;
	var result = 0;

	$("*").each(function(){
		var zIndex = $(this).css("z-index") === "auto" ? 1 : parseInt($(this).css('z-index'));
		if(zIndex > result){
			result = zIndex;
		}
	});

	return MAX > result ? result + 1 : MAX;
}; //end: var zIndex = function(){

/*
* 문자열의 첫 글자만 대문자로 변경한다.
*/
String.prototype.cap = function(){
	return this.charAt(0).toUpperCase() + this.slice(1);
} //end: String.prototype.cap = function(){

var cap = function(str){
	return str.cap();
}

/*
* 문자열에 3자리마다 comma를 추가한다.
*/
String.prototype.comma = function(){
	return this.replace(/\B(?=(\d{3})+(?!\d))/g, ","); 
} //end: String.prototype.comma = function(){

var comma = function(str){
	return str.comma();
} //end: var comma = function(str){

/*
* 문자열에서 comma를 제거한다.
*/
String.prototype.strip = function(){
	return this.replace(/[^\d]+/g, "");
} //end: String.prototype.strip = function(){

var strip = function(str){
	return str.strip();
}

/*
* 만 나이 14세를 체크함.
* 년월이 같아도 날짜가 지나지 않으면 만14세가 안되는 것으로 처리됨.
* 만 14세 미만 true, 만 14세 이상 false
*/
String.prototype.isCriminalMinor = function(){
	var year = Number(this.substr(0, 4));
	var month = Number(this.substr(4, 2));
	var date = Number(this.substr(6));
	var now = new Date();
	var yyyy = Number(now.getFullYear());
	var mm = Number(now.getMonth() + 1);
	var dd = Number(now.getDate())

	return yyyy - year > 14 ? (yyyy - year === 14 ? (mm < month ? true : (mm === month ? (dd > date ? true : false) : false)) : false) : true;
} //end: String.prototype.isCriminalMinor = function(){

var isCriminalMinor = function(str){
	return str.isCriminalMinor();
}

/*
* url/map 패턴의 문자열을 json형태로 parsing한다.
* separator는 첫번째 구분자
* decouple은 key와 value로 나눌 구분자
* 첫번째 물음표, 모든 {(left brace), }(right brace) 기호는 제거된다.
*/
String.prototype.jsonize = function(separator, decouple){
	var json = {};

	var arr = $.trim(this).replace(/^[\?]/, "").replace(/{/gi, "").replace(/}/gi, "").split(separator || ",");
	for(var i = 0; i < arr.length; i++){
		var pair = arr[i].split(decouple || "=");
		if(pair.length === 2 && $.trim(pair[0]) !== ""){
			json[$.trim(pair[0])] = $.trim(pair[1]);
		}
	}

	return json;
} //end: String.prototype.jsonize = function(separator, decouple){

var jsonize = function(str, separator, decouple){
	return str.jsonize(separator, decouple);
}

/*
* jquery를 사용할 때, Object를 prototype을 이용해서 개발할 경우 에러가 발생하므로
* json과 같이 Object를 다룰때는 별도의 객체에 담아서 아래와 같이 처리해야한다.
*/
var $J = {
	/*
	* 배열내 json객체를 지정된 key명으로 정/역방향 정렬한다.
	*/
	sort: function(arr, key, order){
		return arr.sort(function(a, b){
			return ["desc", "dsc"].indexOf(order) === -1 ? (a[key] < b[key] ? - 1 : a[key] > b[key] ? 1 : 0) : (a[key] > b[key] ? - 1 : a[key] < b[key] ? 1 : 0);
		});
	} //end: , sort: function(arr, key, order){

	/*
	* json 객체를 arr에 해당하는 요소를 제외하고 return한다.
	*/
	, excepts: function(json, arr){
		for(var key in json){
			if(arr.indexOf(key) !== -1){
				delete json[key];
			}
		}
		return json;
	} //end: , excepts: function(json, arr){

	/*
	* 입력받은 json객체를 직렬화한다.
	* options는 separator(string), couple(string), excepts(array), empty(boolean)를 가진다.
	* json만 넘길경우엔 jquery의 직렬화와는 조금 다른 결과가 나온다. jquery의 serialize() 경우엔 한글이 인코딩된다.
	* separator는 전체 구분자로 사용한다.
	* couple은 key와 value로 묶을 구분자로 사용한다.
	* excepts는 json 내에서 제외할 key를 배열로 입력해서 제외 처리한다.
	* empty는 value가 공백인 값을 허용할지 결정한다. true는 허용, false는 제외
	*/
	, serialize: function(json, options){
		var str = "";
		var defaults = {
			separator: "&"
			, couple: "="
			, excepts: []
			, empty: true
		};
		$.extend(defaults, options);

		if(defaults.excepts.length > 0){
			json = this.excepts(json, defaults.excepts);
		}

		for(var keys = Object.keys(json), i = 0; i < keys.length; i++){
			var key = keys[i];

			if(!defaults.empty && json[key] === ""){
				continue;
			}

			str += key + defaults.couple + json[key] + (i + 1 === keys.length ? "" : defaults.separator);
		}

		return str;
	} //end: , serialize: function(json, options){
}; //end: var $J = {
