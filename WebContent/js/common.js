function hasJqueryObject( elem ){return elem.length > 0;}

var app = {};
window.onload=function(){ init(); };

function init()
{
	app.$body = $("body");
	//if( hasJqueryObject( app.$body.find(".select") ) ) initZelect();
	if( hasJqueryObject( app.$body.find(".input-text") ) ) initInput();
}

/* select 스타일*/
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
