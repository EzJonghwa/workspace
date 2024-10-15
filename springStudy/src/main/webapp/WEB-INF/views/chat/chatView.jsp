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
                     <c:forEach items= "${chatList }" var ="chat">
                           <!-- 내 채팅일 경우 -->
                           <c:if test ="${sessionScope.login.memId eq chat.memId }">
                           <ul>
                              <li data-no="" class="me pr-2">
                                 <strong class="">${chat.memId}(${chat.memNm})</strong>
                                 <img src="${sessionScope.login.profileImg}"
											id="myImage" class="rounded-circle img-thumbnail shadow-sm"
											width="50" style="cursor: pointer;">
                                 <div class="me">
                                    <p class='myChat chat-box text-left p-3'>${chat.chatMsg }</p>
                                        <strong style="display : inline;" class="align-self-end">${chat.sendDate }</strong>
                                 </div>
                              </li>
                           </ul>
                           </c:if>
                           <!-- 다른사람의 채팅일 경우 -->
                           <c:if test ="${sessionScope.login.memId ne chat.memId }">
                           <ul>
                              <li data-no="" class="pl-2">
                                 <strong>${chat.memId}(${chat.memNm})</strong>
                                 <img src="${pageContext.request.contextPath}${chat.profileImg}"
											id="myImage" class="rounded-circle img-thumbnail shadow-sm"
											width="50" style="cursor: pointer;">
                                 <div>
                                    <p class='chat-box bg-light p-3'>${chat.chatMsg }</p>
                                    <strong style="display : inline;" class="align-self-center">${chat.sendDate }</strong>
                                 </div>
                              </li>
                           </ul>
                           </c:if>
                     </c:forEach>
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
      <!--SockJS websocket을 지원하지 않는 브라우저나 네트워크 환경에서 양방향 통신을 지원함. -->
      <script src="<c:url value="/js/sockjs.min.js" />"></script>
      <!--STOMP 특정 주제를 구독하고 메세지를 보내고 받는 채팅, 알람등의 기능을 제공함. -->
      <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>   
      <script>
        $(document).ready(function(){
              var client;
              var chatBox =$(".box");
              var memId = '${sessionScope.login.memId}';
              var memNm= '${sessionScope.login.memNm}';
              var profileImg = '${sessionScope.login.profileImg}';
              var roomNo ='${roomNo}';
              scroll_down();
              
              var sock = new SockJS("<c:url value='/endpoint' />");
              client = Stomp.over(sock);
              console.log(client);
              
              //최초 연결이 맺어지면 실행
              client.connect({"userId":memId, "roomNo":roomNo}, function(){
                    //상대방이 보낸 메세지를 전달 받았을때;
                    client.subscribe("/subscribe/chat/"+ roomNo, function(chat){
                       // 룸 넘버에따라 해당 룸 내용을 구독한다.
                       var body = JSON.parse(chat.body);
                       if(body.type == 'notification'){
                          var html = "<div class='notification'>"+body.message +"</div>";
                          $("#list-guestbook").append(html);
                       }else{
                          $("#list-guestbook").append(renderList(body));
                       }
                       scroll_down();
                    });
                    
              });
              
             //메세지 전송
             function sendmsg(){
                var msg = $("#msgi").val();
                if(msg == ""){
                   return false;
                }
                client.send("/app/hello/" + roomNo, {},JSON.stringify({
                   // 룸 넘버를 받아서 멧세지 전송
                       chatMsg: msg
                      ,memId : memId
                      ,roomNo: roomNo
                      ,memNm : memNm
                      ,profileImg : profileImg
                      
                }));
                $("#msgi").val('');
             }
             // 메세지 출력
             function renderList(vo){
                var str ="";
                var content =""
                if(vo.memId == memId){
                   content ="<p class='myChat chat-box text-left p-3'>"+vo.chatMsg+"</p>";
                   str= "<li data-no='' class='me pr-2'>"
                     +"<strong class=''>"+vo.memId+"("+vo.memNm+")"+"</strong>"
                     +"<img src="+vo.profileImg+" id='myImage' class='rounded-circle img-thumbnail shadow-sm'width='50' style='cursor: pointer;'>"
                     +"<div class='me'>"+content
                     +"</div>"
                     +"<strong style='display : inline;' class='align-self-end'>"+vo.sendDate+"</strong>"
                     +"</li>";

                // 다른 사람
                }else{
                   content ="<p class='chat-box bg-light p-3'>"+vo.chatMsg+"</p>";
                   str= "<li data-no='' class='pr-2'>"
                     +"<strong class=''>"+vo.memId+"("+vo.memNm+")"+"</strong>"
                     +"<img src="+vo.profileImg+" id='myImage' class='rounded-circle img-thumbnail shadow-sm'width='50' style='cursor: pointer;'>"
                     +"<div>"+content
                     +"</div>"
                     +"<strong style='display : inline;' class='align-self-end'>"+vo.sendDate+"</strong>"
                     +"</li>";
                }
                return str;
             }
             $("#btnSend").click(function(){
                sendmsg();
             });
             $("#msgi").keydown(function(e){
                if(e.keyCode == 13){
                   // 엔터의 키코드는 13
                   sendmsg();
                }
             });
             $("#btnOut").click(function(){
                disconnect();
                location.href ="<c:url value='/chatListView'/>";
             });
             window.onbeforeunload = function(){
                disconnect();
             }
             //나가기
             function disconnect(){
                if(client != null){
                   // 종료 직전 메세지를 방 참여자들에게~
                   client.send("/subscribe/char/"+roomNo,{},
                         JSON.stringify({"type":"notification","message":memId +"님이 나가셨습니다..."}));
                   client.disconnect();
                }
             }
             // 최초 연결이 맺어지면 실행
             // 메세지 전송 버튼 클릭시
             
             // 스크롤 기능
             function scroll_down(){
                $(".chatcontent").scrollTop($(".chatcontent")[0].scrollHeight);
             }
        });
      </script>
    </body>
</html>


