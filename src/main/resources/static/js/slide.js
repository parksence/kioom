var slider = {
   mainVisualSetting: {
      dots: true,
      fade: true,
      speed: 350,
      arrows: false,
      autoplay : true, 
      autoplaySpeed : 3000,
      cssEase: 'ease-out',
      appendDots: '#slider_nav'
   },
   type01: {
      dots: false,
      infinite: true,
      arrows:true,
      speed: 300,
      slidesToShow: 3,
      slidesToScroll: 1,
      centerPadding:0,
      fade:false,
      adaptiveHeight: true,
      autoplay : true, 
      autoplaySpeed : 3000,
      cssEase: 'ease-out',
      appendArrows: '.section_wrapper',
      vertical:true,
      verticalSwiping:true,
   },
   type02: {
      dots: false,
      infinite: true,
      speed: 700,
      slidesToShow: 3,
      slidesToScroll: 3,
      cssEase: 'ease-in-out',
      fade:false,
   },
   centerMode: {
      dots: false,
      infinite: true,
      speed: 1000,
      slidesToShow: 3,
      slidesToScroll: 3,
      centerPadding: '6rem',
      centerMode: true,
      fade:false,
   },
   navMode: function(sliderFor, sliderNav) {
      $(sliderFor).slick({
         slidesToShow: 1,
         slidesToScroll: 1,
         arrows: false,
         fade: true,
         asNavFor: sliderNav,
      });
      $(sliderNav).slick({
         slidesToShow: 3,
         slidesToScroll: 1,
         asNavFor: sliderFor,
         dots: false,
         centerMode: true,
         focusOnSelect: true,
         infinite: false,
         responsive: [
            {
               breakpoint: 767,
               settings: {
                  slidesToShow: 2,
                  centerMode: false,
               }
            },
         ]
      });
   },
   funcMainVisual: function(targetName) {
      $(targetName).slick(this.mainVisualSetting);
   },
   funcType01: function(targetName) {
      $(targetName).slick(this.type01);
   },
   funcType02: function(targetName) {
      $(targetName).slick(this.type02);
   },
   funcCenterMode: function(targetName) {
      $(targetName).slick(this.centerMode);
   },
   funcNavSlider: function(sliderFor, sliderNav) {
      return this.navMode(sliderFor, sliderNav);
   }
};