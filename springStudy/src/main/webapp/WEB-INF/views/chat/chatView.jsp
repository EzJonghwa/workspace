<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>채팅</title>
        <style>
         .chat-containerK {
            /* overflow: hidden; */
            width : 100%;
            /* max-width : 200px; */
         }
         .chatcontent {
            height: 700px;
            width : 100%;
            /* width:300px; */
            overflow-y: scroll;
         }
         .chat-fix {
            position: fixed;
            bottom: 0;
            width: 100%;
         }
         #alertK{
            display : none;
         }
         #msgi{   
            resize: none;
         }
         .myChat{
            background-color : #E0B1D0;
         }
         li{
            list-style-type:none;
         }
         .chatBox{
            display : inline-block;
         }
         .chatBox dateK{
            vertical-align: text-bottom;
         } 
         .me{
            text-align : right;
            /* text-align:center; */
         }
         .chat-box{
            max-width : 200px;
            display: inline-block;
            border-radius: 15px;
         }
         .notification{
            text-align : center;
         }
      </style>
    </head>
    <body id="page-top">
        <!-- 모든 페이지 상단에 들어가는 부분 -->
       <jsp:include page="/WEB-INF/inc/top.jsp" />
        <!-- Contact Section-->
        <section class="page-section" id="contact">
            <div class="container" style="margin-top: 100px;">
                <div id="chat-containerK">
               <div class="chatWrap">
                  <!-- 방정보 -->
                  <div class="main_tit">
                     <h1>방 정보: [] </h1>
                  </div>
                  <div class="content chatcontent border border-secondary" data-room-no="" >
                     <div id="list-guestbook" class="">
                           <!-- 내 채팅일 경우 -->
                           <ul>
                              <li data-no="" class="me pr-2">
                                 <strong class="">내 이름</strong>
                                 <div class="me">
                                    <p class='myChat chat-box text-left p-3'>메세지 ^^ </p>
                                        <strong style="display : inline;" class="align-self-end">2024-10-14 14:20:05</strong>
                                 </div>
                              </li>
                           </ul>
                           <!-- 다른사람의 채팅일 경우 -->
                           <ul>
                              <li data-no="" class="pl-2">
                                 <strong>다른 사람 이름</strong>
                                 <div>
                                    <p class='chat-box bg-light p-3'>메세지 ^_____^</p>
                                    <strong style="display : inline;" class="align-self-center">2024-10-14 14:20:05</strong>
                                 </div>
                              </li>
                           </ul>
                     </div>
                  </div>
                  <div>
                     <div class="d-flex justify-content-center" style="height: 60px">
                        <input type="text" id="msgi" name="msg" class="form-control" style="width: 75%; height: 100%">
                        <button type="button" id="btnSend" class="send btn btn-primary" style="width: 25%; height: 100%">보내기</button>
                        <button type="button" id="btnOut" class="btn btn-secondary " style="width: 25%; height: 100%">나가기</button>
                     </div>
                  </div>
               </div>
            </div>
            </div>
        </section>

        <!-- 모든 페이지 하단에 들어가는 부분 -->
        <!-- Footer-->
      <jsp:include page="/WEB-INF/inc/footer.jsp" />
      <!-- SockJS websocket을 지원하지 않는 부라우저나 네트워크 환경에서 양방향 통신을 지원함. -->
      <script src="<c:url value="/js/sockjs.min.js" />"></script>
      <!-- STOMP 특정 주제를 구독하고 메세지를 보내고 받는 채팅, 알람등의 기능을 제공함.-->
      <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>   
      <script>
        $(document).ready(function(){
           var client;
           var charBox =$(".box");
           var memId ='${sessionScope.login.memId}'
           var roomNo ='${roomNo}';
           
           var sock =new SockJS("<c:url value='/endpoint'/>");
           client =Stomp.over(sock);
           console.log(client);
           
           // 최초 연결이 맺어지면 실행
           client.connect({"userId":memId, "roomNo":roomNo}, function() {
                     //상대방이 보낸 메세지를 전달 받았을때; 
                     client.subscribe("/subscribe/chat/" + roomNo, function(chat){
                        var body =JSON.parse(chat.body);
                        if(body.type == 'notification'){
                           var html ="<div class='notification'>"+body.message +"</div>";
                           $("#list-guestbook").append(html);
                           }else{
                              console.log(body);
                        }
                     });
           });
           
             //메세지 전송
             function sendmsg(){
                var msg = $("#msgi").val();
                if(msg ==""){
                   return false;
                }
                
                client.send("/app/hello" + roomNo, {},JSON.stringify({
                     "chatMsg" : msg
                     ,"memId"  : memId
                     ,"roomNo" : roomNo
                }));
                $("#msgi").val('');
             }
             $("#btnSend").click(function(){
                sendmsg();
             });
             $("#msgi").keydown(function(e){
                if(e.keyCode == 13) {
                   sendmsg();
                }
             });
             //메세지 출력
             function renderList(vo){
             }
             //나가기
             function disconnect(){
             }
             //최초 연결이 맺어지면 실행
             // 메세지 전송 버튼 클릭시
        });
      </script>
    </body>
</html>


