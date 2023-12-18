$(function() {
   var $print = $('.print');
   var $datePicker = $( ".datepicker" );
   var $datePicker_readOnly = $( ".datepicker:read-only" );
   var datePicker_setting = {
      dateFormat: "yy-mm-dd",
      monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
      monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
      dayNames: ['일', '월', '화', '수', '목', '금', '토'],
      dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
      dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
      weekHeader: "주",
      yearSuffix: '년'
   };

   
   $datePicker.length && $datePicker.datepicker(datePicker_setting);

   $datePicker_readOnly.length && $datePicker_readOnly.datepicker('disable', true);


   $print.on('click', function() {
      window.print();
   });






   // 전체 체크박스
   $('[data-wrap*="checkbox_body"]').on('change', function(e) {
      var $this = $(this);
      var target = $(e.target).attr('data-checkbox') === 'all_check' ? $(e.target) : false;
      var targetChecked = e.target.checked;
      var $allCheck = $this.find('input[data-checkbox="all_check"]');
      var $inputElems = $this.find('input[type="checkbox"]:checked:not([data-checkbox="all_check"])');
      var $inputAll = $this.find('input[type="checkbox"]:not([data-checkbox="all_check"])');
      var inputAllLength = $inputAll.length
      var inputLength = $inputElems.length;

      if(target) {

         if(targetChecked) {
            $inputAll.prop('checked', true);
         } else {
            $inputAll.prop('checked', false);
         }

         return;
      }


      if(inputAllLength === inputLength) {
         $allCheck.prop('checked', true);
      } else {
         $allCheck.prop('checked', false);
      }
   });

});



/* ===========================================================================================
   debounce
=========================================================================================== */
function debounce(callback, limit = 100) {
   var timeout = null;
   return function(args) {
       clearTimeout(timeout);
       timeout = setTimeout(function() {
           callback.apply(this, args)
       }, limit);
   };
};
/* ===========================================================================================
   throttle
=========================================================================================== */
function throttle(callback, limit = 100) {
    var waiting = false;
    return function() {
        if(!waiting) {
            callback.apply(this, arguments);
            waiting = true;
            setTimeout(() => {
                waiting = false;
            }, limit);
        }
    }
};


/* ===========================================================================================
               chart color
=========================================================================================== */


