var modal_module = {
   OVERFLOW: 'overflow',
   ACTIVE: 'active',
   $body: () => $('body'),
   $loading: () => $('.modal.loading'),
   // 로딩창 열기
   loaderOpen: function() {
      this.$body().addClass(this.OVERFLOW);
      this.$loading().addClass(this.ACTIVE);
   },
   // 로딩창 닫기
   loaderClose: function() {
      this.$body().removeClass(this.OVERFLOW);
      this.$loading().removeClass(this.ACTIVE);
   },
   // 검증 후 모달 열기 
   modalOpen: function(that, func) {
      
      if(func === false || func === undefined) {
         return;
      }

      var target = $(that).attr('data-modal');

      this.$body().addClass(this.OVERFLOW);
      $('.'+target).addClass(this.ACTIVE);
   },
};


// 로딩창 열기
var loaderOpen = modal_module.loaderOpen.bind(modal_module);
// 로딩창 닫기
var loaderClose = modal_module.loaderClose.bind(modal_module);
 // 검증 후 모달 열기 
var modalOpen = modal_module.modalOpen.bind(modal_module);
