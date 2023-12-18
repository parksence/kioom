$(function() {
   var ACTIVE= 'active';
   var ON = 'on';
   var OVERFLOW= 'overflow';
   var SITEMAP= 'siteMap';
   var DATA_MODAL = 'data-modal';
   var ASIDE = 'aside';
   var MODAL_BG = 'modal_bg';   
   var DEPTH_MENU = 'depthMenu';
   var DEPTH2_WRAP = 'depth2_wrap';
   var $html= $('html');
   var $body= $('body');
   var $header= $('#header');
   var $aside= $('#aside');
   var $siteMapBtn= $(".siteMap-btn");
   var $depth1_title= $(".depth1_title");
   var $modal_bg = $('.' + MODAL_BG);
   var $modal_open = $('.modal_open');
   var $modal_close = $('.modal_close');
   var $tab_nav = $('#tab_nav > a.active');

   $html.on('click', function(e) {
      var $target= $(e.target);
      var siteMap_backDrop_id = $target.attr('id') === ASIDE ? true : false;

      if($target.hasClass(MODAL_BG)) {
         $target.removeClass(ACTIVE);

         if($modal_bg.hasClass(ACTIVE)) {
            return;
         }
   
         $body.removeClass(OVERFLOW);
      }

      if(siteMap_backDrop_id) {
         $body.removeClass(OVERFLOW);
         $aside.removeClass(SITEMAP);
         $siteMapBtn.removeClass(ACTIVE);
      }


      if($target.hasClass(DEPTH_MENU)) {
         $header.removeClass(SITEMAP);
         $body.removeClass(OVERFLOW);
         $siteMapBtn.removeClass(ACTIVE);
      }
   });
   
   //aside menu
   function asideMenuToggle(event) {
      var $this = $(this); 
      var depth2MenuCheck = $this.siblings().hasClass(DEPTH2_WRAP);

      if(!depth2MenuCheck) return;

      $(this).toggleClass(ACTIVE);

      event.preventDefault();
   };
   

   //site-map
   function siteMapToggle() {
      $body.toggleClass(OVERFLOW);
      $aside.toggleClass(SITEMAP);
      $(this).toggleClass(ACTIVE);
   };
   
   $siteMapBtn.on('click', siteMapToggle);
   $depth1_title.on('click', asideMenuToggle);
   $tab_nav.on('click', function(){ 
      return false;
   });
/* ===========================================================================================
   modal
=========================================================================================== */
   //  modal open
   $modal_open.on('click', function() {
      var $this = $(this);
      var targetModal = $this.attr(DATA_MODAL);

      $('.' + targetModal).addClass(ACTIVE);

      $body.addClass(OVERFLOW);
   });

   // modal close
   $modal_close.on('click', function(e) {
      var $this = $(this);
      var targetModal = $this.attr(DATA_MODAL);

      $('.' + targetModal).removeClass(ACTIVE);

      if($modal_bg.hasClass(ACTIVE)) {
         return;
      }
      
      $body.removeClass(OVERFLOW);

      e.stopPropagation();
   });
});