describe("LoginController", function() {

    beforeEach(module('ngCookies','chatApp'));
    var $controller;
    var $scope;
    var cookies;
    var window;
    var login_controller;
    var $httpBackend;
    var LoginServiceMock;

    beforeEach(inject(function (_$controller_,_$rootScope_, $cookies,$injector,$window) {
        $controller = _$controller_;
        $scope = _$rootScope_.$new();
        cookies = $cookies;
        window = $window;
        $httpBackend = $injector.get('$httpBackend');
        LoginServiceMock = $injector.get('LoginService');

        login_controller =
            $controller('LoginController',
                {$scope: $scope,$injector: $injector,$cookies: $cookies,$window: $window,  LoginService: LoginServiceMock});

    }));


    it("should initialize in controller", function() {
        spyOn(cookies, "get").and.returnValue(undefined);
        login_controller.init();
        expect(window.location.href).toContain("specrunner")
    });

    it("should login", function() {
        spyOn(cookies, "get").and.returnValue("success");
        login_controller.login();
        expect(cookies.get("id")).toBe("success");
    });


    it('should get login', function() {
        $httpBackend.whenGET('/user').respond({id:'abc',name:'burak'});
        LoginServiceMock.login().then(function(response) {
            expect(response.data).toEqual({id:'abc',name:'burak'});
        });
        $httpBackend.flush();
    });
});