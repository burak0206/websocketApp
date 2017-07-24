describe("ChatController", function() {

    beforeEach(module('ngCookies','chatApp'));
    var $controller;
    var $scope;
    var cookies;
    var window;
    var chat_controller;
    var $httpBackend;
    var ChatServiceMock;

    beforeEach(inject(function (_$controller_,_$rootScope_, $cookies,$injector,$window) {
        $controller = _$controller_;
        $scope = _$rootScope_.$new();
        cookies = $cookies;
        window = $window;
        $httpBackend = $injector.get('$httpBackend');
        ChatServiceMock = $injector.get('ChatService');

        chat_controller =
            $controller('ChatController',
                {$scope: $scope,$injector: $injector,$cookies: $cookies,$window: $window,  ChatService: ChatServiceMock});

    }));

    it("should initialize in controller", function() {
        spyOn(cookies, "get").and.returnValue("success");
        chat_controller.init();
        expect(window.location.href).toContain("specrunner")
    });

    it("should remove cookies when logout", function() {
        spyOn(chat_controller,"logout");
        expect(cookies.get("id")).toBe(undefined);
        expect(cookies.get("username")).toBe(undefined);
    });

    it("should message equals empty when add message", function() {
        spyOn(ChatServiceMock, "send");
        chat_controller.message = "notempty";
        chat_controller.addMessage()
        expect(chat_controller.message).toBe("");
    });

    it("should messages equals empty when clear", function() {
        chat_controller.messages = ["Banana", "Orange", "Apple", "Mango"];
        chat_controller.clear()
        expect(chat_controller.messages.length).toBe(0)
    });

    it("should color be green", function() {
        var result = chat_controller.set_color("you")
        expect(result.color).toBe("green")
    });

    it("should color be red", function() {

        var result = chat_controller.set_color("he")
        expect(result.color).toBe("red")
    });

});
