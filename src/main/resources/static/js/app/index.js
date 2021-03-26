var main = {
    init : function(){      //init이라는 이름으로 실행되는 익명메소드
        var _this = this;
        $('#btn-save').on('click', function(){  //등록 버튼
                _this.save();  //(_this는 js파일) js파일에서 save 메소드 호출
        });

        $('#btn-update').on('click', function(){
              _this.update();
        });

       $('#btn-delete').on('click', function(){
               _this.delete();
        });

    },
    save : function(){
        var data = {    //값을 가져와서 데이터를 꾸리고 서버에 ajax로 날려줌
            title: $('#title').val(), //id가 title인 것의 값을 가져옴
            author: $('#author').val(),
            content: $('#content').val()
        };


        $.ajax({        //ajax로 서버에 전송해줌
            type: 'POST',
            url: '/api/v1/posts',   //postsapicontroller`
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data) //json형식으로 바꿔서 보냄
            }).done(function(){
                alert('글이 등록되었습니다');
                window.location.href = '/'; // url이 /상태로 돌아가도록
            }).fail(function(error){
                alert(JSON.stringify(error)); //에러 메세지를 json형태로 알람 띄움
            });
    },

    update : function(){
        var data ={
            title:$('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType:'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('글이 수정되었습니다.');
            window.location.href='/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
   },
   delete : function(){
        var id = $('#id').val();

        $.ajax({
            type:'DELETE',
            url:'/api/v1/posts/'+id,
            dataType:'json',
            contentType:'application/json; charset=utf-8'
        }).done(function(){
            alert('글이 삭제되었습니다.');
            window.location.href='/';
        }).fail(function(error){
            alert(JSON.stringify(error));

        });
    }

 };
 main.init();

/* Create: POST Read: GET Update: PUT Delete: DELETE */



