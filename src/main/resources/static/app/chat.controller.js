/**
 * Created by burakdagli on 27.06.2017.
 */
(function () {
        'use strict';
        angular
            .module('chatApp',['ngCookies'])
            .controller('ChatController', ChatController)
            .service('ChatService', ChatService)

        ChatController.$inject = ['$scope','$injector','$cookies','$window','ChatService'];

        function ChatController($scope,$injector,$cookies,$window,ChatService) {
            var chat_controller = this;
            chat_controller.message = "";
            chat_controller.messages = [];
            chat_controller.participants = [];

            chat_controller.init =  function(){
                if ($cookies.get("id")) {
                    console.log("User logged");
                } else {
                    console.log("User is not logged");
                    $window.location = "/"
                }
            }

            chat_controller.logout = function () {
                var id = $cookies.get('id');
                var name = $cookies.get('username');
                ChatService.logout(id,name);
                $cookies.remove('username');
                $cookies.remove('id');
                $window.location = "/"
            }

            chat_controller.addMessage = function() {
                ChatService.send(chat_controller.message);
                chat_controller.message = "";
            };

            chat_controller.clear =  function () {
                chat_controller.messages = [];
            }

            ChatService.receive().then(null, null, function(message) {
                console.log("ChatService.receive");
                console.log("log: "+JSON.parse(message).text);
                if(JSON.parse(message).id==$cookies.get('id')){
                    var msg = {
                        author: 'you',
                        text: JSON.parse(message).text

                    };
                    chat_controller.messages.push(msg);
                }else {
                    var msg = {
                        author: JSON.parse(message).author,
                        text: JSON.parse(message).text

                    };
                    chat_controller.messages.push(msg);
                }
            });

            ChatService.retrieveParticipants().then(null, null, function(data) {
                console.log("ChatService.retrieveParticipants");
                console.log("log: "+JSON.parse(data));
                chat_controller.participants = JSON.parse(data);
            });

            ChatService.addParticipant().then(null, null, function(data) {
                console.log("ChatService.retrieveParticipants");
                console.log("log: "+JSON.parse(data));
                chat_controller.participants.push(JSON.parse(data));
            });

            ChatService.removeParticipant().then(null, null, function(data) {
                console.log("ChatService.removeParticipants");
                console.log("log: "+JSON.parse(data));
                chat_controller.participants = chat_controller.participants.filter(function(item){
                    return JSON.parse(data).id != item.id;
                });

            });

            chat_controller.set_color = function (message) {
                if (message=='you') {
                    return { color: "green" }
                } else {
                    return { color: "red" }
                }
            }
            console.log($injector.annotate(ChatController));
        }

        ChatService.$inject = ['$q','$http','$timeout','$cookies'];

        function ChatService($q,$http,$timeout,$cookies) {
            var service = this;
            var listener = $q.defer();
            var listenerParticipant = $q.defer();
            var listenerAddParticipant = $q.defer();
            var listenerRemoveParticipant = $q.defer();

            service.socket =  null;
            service.stomp= null;

            service.socket = new SockJS('/chat-demo');
            service.stomp = Stomp.over(service.socket);
            service.stomp.connect({}, function (frame) {
                console.log('Connected: ' + frame);
                service.stomp.subscribe('/topic/newMessage', function (data) {
                    console.log("listener.notify trigger");
                    listener.notify(service.getMessage(data.body));
                });

                service.stomp.subscribe("/app/participants", function(data) {
                    console.log("listenerParticipant.notify trigger");
                    listenerParticipant.notify(data.body);
                });

                service.stomp.subscribe("/topic/add/participants", function(data) {
                    console.log("listenerAddParticipant.notify trigger");
                    listenerAddParticipant.notify(data.body);
                });

                service.stomp.subscribe("/topic/remove/participants", function(data) {
                    console.log("listenerRemoveParticipant.notify trigger");
                    listenerRemoveParticipant.notify(data.body);
                });

            });

            service.getMessage = function (data) {
                console.log("service.getMessage");
                return data;
            };

            service.retrieveParticipants =  function() {
                console.log("listenerParticipant.promise trigger");
                return listenerParticipant.promise;
            };

            service.addParticipant =  function() {
                console.log("listenerAddParticipant.promise trigger");
                return listenerAddParticipant.promise;
            };

            service.removeParticipant =  function() {
                console.log("listenerRemoveParticipant.promise trigger");
                return listenerRemoveParticipant.promise;
            };

            service.send =  function (message) {
                service.stomp.send("/app/hello", {}, JSON.stringify({ 'text': message, 'author': $cookies.get('username'), 'id': $cookies.get('id') }));

            }

            service.logout =  function (id,name) {
                service.stomp.send("/app/logout", {}, JSON.stringify({ 'id': id, 'name': name }));

            }

            service.receive = function() {
                console.log("listener.promise trigger");
                return listener.promise;
            };
        }
    }
)();