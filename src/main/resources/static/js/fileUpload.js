// 파일 목록을 표시하기 위한 HTML 요소로를 생성
function fileListElement(target, fileName, dataValue, index) {
   var elem = "<div class=\"file_arg\">" +
   "<a href=\"#\">"+ fileName +"</a>"+
   "<button class=\"file_delete\" data-target="+ target +" onclick=\"fn_delete_fileName(this,'"+ dataValue +"','" + index + "')\" type=\"button\">"+
   "<i class=\"xi-close-min\"></i></button>"+
   "</div>";

   return elem;
};

// 파일이 첨부되지 않았을 때 보여줄 메시지를 생성하는 함수
function nonFileElement() {
   var elem = "<p class='file_placeholder fontSize_15 fontColor_8C8C8C'>파일을 첨부해주세요</p>";
   return elem;
};

// 파일을 처리하고 화면에 표시하는 로직
function fn_fileNameView(that, dataValue, max) { 
   var $fileCart = $('[data-filewrapper="'+ that.id +'"]');
	var dataTransfer = new DataTransfer();
	var files = $(that)[0].files;
	var add_files = Array.from(files); // 파일을 배열에 넣는다.

   if(files.length) {
      if(that.multiple) {
         fileData[dataValue] = fileData[dataValue].concat(add_files); //기존에 배열에 새로운 배열을 합한다.

         if(fileData[dataValue].length > max) {
            fileData[dataValue] = fileData[dataValue].slice(0, max);
            alert('첨부파일 최대 갯수는' + max +'개 입니다.');
         };

         fileData[dataValue].forEach(function(files) {
            dataTransfer.items.add(files);
         });

         //[공통-파일명 노출]
         $fileCart.children().remove(); // 첨부된 파일명이 보여질 영역 초기화

         $(that)[0].files = dataTransfer.files; //변환된 파일을 input file에 넣는다..

         fileData[dataValue].forEach(function(arg, index) {
            $fileCart.append(fileListElement(that.id, arg.name, dataValue, index));
         });
         
         return;
      };

      fileData[dataValue] = add_files;

      fileData[dataValue].forEach(function(files) {
         dataTransfer.items.add(files);
      });

      $(that)[0].files = dataTransfer.files;
      
      $fileCart.html(fileListElement(that.id, files[0].name, dataValue, 0));

      return;
   };

   fileData[dataValue].forEach(function(files) {
      dataTransfer.items.add(files);
   });

   $(that)[0].files = dataTransfer.files;

};

// 파일 삭제 함수
function fn_delete_fileName(that, dataValue, del_index) { //파일명과 , 삭제할 파일의 index를 받는다.
	var dataTransfer = new DataTransfer();
   var target = $(that).attr('data-target');
	var files = $("#"+ target )[0].files; // 현재 파일의 목록을 받아서
   var $fileList = $('[data-filewrapper="'+ target +'"]');
	var fileArray = Array.from(files); //배열에 저장시킨다.
	fileArray.splice(del_index, 1); //삭제한 파일의 index부터 1개의 배열을 잘라낸다.(= 삭제)
   
   fileArray.forEach(function(files) {
      dataTransfer.items.add(files);
   });

   //[공통-파일명 노출]
   $fileList.children().remove();

	$("#"+target)[0].files = dataTransfer.files; //변환된 파일을 input file에 넣는다.

   fileData[dataValue] = fileArray;

   if(fileArray.length) {
      fileArray.forEach(function(arg, index) {
         $fileList.append(fileListElement(target, arg.name, dataValue, index));
      });
   } else {
      $fileList.append(nonFileElement());
   };

};

// 파일 업로드 모듈
const fileUploadModule = {
   XLS: 'application/vnd.ms-excel',
   XLSX: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
   CSV: 'text/csv',
   MAX_FILE_SIZE: 1000000,
   // input file 업로드 
   asyncFileNameView: function(that) {
      const { files } = that;
      const $fileCart = $('[data-filewrapper="'+ that.id +'"]');
      let add_files = Array.from(files); // 파일을 배열에 넣는다.

      if(!maximumFileSizeCheck(add_files)) return;

      add_files.forEach(function(arg) {
         $fileCart.append(asyncFileListElement(arg));

         // 엑셀 업로드
         var formData = new FormData();
         formData.append('file', arg);

         $.ajax({
            url:'/api/uploadExcel',
            method:'post',
            data:formData,
            contentType: false,
            processData: false,
            success: function (response) {
               console.log('response', response)
            },
            error: function (xhr, status, error) {
               if (xhr.responseJSON && xhr.responseJSON.message) {
                  var errorMessage = xhr.responseJSON.message;
                  alert(errorMessage);
               } else {
                  console.error('Error')
               }
            }
         })
      });
   },
   // 드래그 앤 드랍 파일 업로드
   dragDropFile: function(that, files) {
      const $fileCart = $('[data-filewrapper="'+ that.id +'"]');
      let add_files = Array.from(files); // 파일을 배열에 넣는다.

      add_files = fileTypeCheck(add_files);

      if(!add_files.length) return;
      if(!maximumFileSizeCheck(add_files)) return;

      add_files.forEach(function(arg) {
         $fileCart.append(asyncFileListElement(arg));

         // 엑셀 업로드
         var formData = new FormData();
         formData.append('file', arg);

         $.ajax({
            url:'/api/uploadExcel',
            method:'post',
            data:formData,
            contentType: false,
            processData: false,
            success: function (response) {
               console.log('response', response)
            },
            error: function (xhr, status, error) {
               if (xhr.responseJSON && xhr.responseJSON.message) {
                  var errorMessage = xhr.responseJSON.message;
                  alert(errorMessage);
               } else {
                  console.error('Error')
               }
            }
         })
      });
   },
   // 업로드 파일 DOM Element 리스트 추가 
   asyncFileListElement: ({ name, size }) => {
      const fileId_number = $('.file_arguments').length;
   
      const elem = ` 
         <div class="file_arguments" id="file_item_${fileId_number}">
            <div class="file_data">
               <span class="file_name">${name}</span>
               <span class="file_size">Size : ${size} Kb</span>
            </div>
            <div class="upload_result"> Upload success </div>
            <button type="button" onclick="fileDelete('#file_item_${fileId_number}')"><i class="xi-close"></i></button>
         </div>
      `;
   
      return elem;
   },
   // 파일 삭제
   fileDelete: function(target) {
      const $target = $(target);
   
      $target.remove();
   },
   // 파일 용량 체크
   maximumFileSizeCheck: function(files) {
      const fileArray = Array.from(files);
      const { MAX_FILE_SIZE } = this; 
      const findMaximunFileSize = fileArray.findIndex( file => file.size > MAX_FILE_SIZE);
      
      console.log(fileArray,' fileArray');
      console.log(findMaximunFileSize,'findMaximunFileSize')

      if(findMaximunFileSize >= 0) {
         const { name } = fileArray[findMaximunFileSize]
         alert(`${name} 파일크기가 1GB보다 큽니다.`); 

         return false;
      }

      return true;
   },
   // 파일 확장자 체크
   fileTypeCheck: function(files) {
      const fileArray = Array.from(files);
      const { XLS, XLSX, CSV } = this;

      const findFileType = fileArray.findIndex( file => {
         const { type } = file;

         switch(type) {
            case XLS: 
               return type !== XLS;
            case XLSX: 
               return type !== XLSX;
            case CSV: 
               return type !== CSV;
            default:
               return file;
         }
      });
      
      if(findFileType >= 0) {
         const { name } = fileArray[findFileType]
         alert(`${name} 지원하는 확장자가 아닙니다.`); 

         return [];
      }

      const fileFilter = fileArray.filter(file => {
         const { type } = file;
   
         switch(type) {
            case XLS: 
               return type === XLS;
            case XLSX: 
               return type === XLSX;
            case CSV: 
               return type === CSV;
            default:
               return file;
         }
      });
   
      return fileFilter;
   },
   // 파일 리스트 리셋
   resetFileList: function(target) {
      const $fileCart = $('[data-filewrapper="'+ target +'"]');
      $fileCart.children('.file_arguments').remove();

      return true;
   },
};

const { 
   asyncFileNameView,
   dragDropFile,
   asyncFileListElement,
   fileDelete,
   resetFileList,
 } = fileUploadModule;

const maximumFileSizeCheck = fileUploadModule.maximumFileSizeCheck.bind(fileUploadModule);
const fileTypeCheck = fileUploadModule.fileTypeCheck.bind(fileUploadModule);


$(function() {
   const drop_zone = document.getElementById('drop_zone');
   const drag_overElem = document.getElementById('drag_over');
   const ACTIVE = 'active';
   const BLOCK = 'block';
   const NONE = 'none'

   const dropZoneColorModule = {
      hanldeDragenter: function() {
         drag_overElem.style.display = BLOCK;
         drop_zone.classList.add(ACTIVE);
      },
      handleDragover: evnet => event.preventDefault(),
      hanldeDragleave: function() {
         drag_overElem.style.display = NONE;
         drop_zone.classList.remove(ACTIVE);
      },
      hanldeDrop: function(event) {
         event.preventDefault();
         hanldeDragleave();
         const { files } = event.dataTransfer;
         const target = document.getElementById('files');

         // files
         dragDropFile(target, files);
      },
   };

   const { 
      hanldeDragenter,
      handleDragover,
      hanldeDragleave,
      hanldeDrop,
   } = dropZoneColorModule;


   /* 박스 안에 Drag 들어왔을 때 */
   drop_zone && drop_zone.addEventListener('dragenter', hanldeDragenter);
   /* 박스 안에 Drag를 하고 있을 때 */
   drop_zone && drop_zone.addEventListener('dragover', handleDragover);
   /* 박스 밖으로 Drag가 나갈 때 */
   drag_overElem && drag_overElem.addEventListener('dragleave', hanldeDragleave);
   /* 박스 안에서 Drag를 Drop했을 때 */
   drop_zone && drop_zone.addEventListener('drop', hanldeDrop);
});
