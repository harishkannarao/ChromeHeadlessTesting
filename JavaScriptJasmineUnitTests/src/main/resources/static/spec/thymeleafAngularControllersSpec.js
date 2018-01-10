describe('thymeleafAngularControllers controllers', function() {

  describe('DemoCtrl', function(){
    var scope, ctrl, window;

    // Load our app module definition before each test.
    beforeEach(module('thymeleafAngularControllers'));

    // Load our controller before each test.
    beforeEach(inject(function($rootScope, $controller, $window) {
        window = $window
        window.defaultFirst = '3';
        window.defaultSecond = '2';
        scope = $rootScope.$new();
        ctrl = $controller('DemoCtrl', {$scope: scope, $window: window});
    }));

    it('should add two numbers with default values', function() {
        expect(scope.first).toBe('3');
        expect(scope.second).toBe('2');
        expect(scope.result).toBeUndefined();
        scope.addNumbers();
        expect(scope.result).toBe(5);
    });

    it('should subtract two numbers with default values', function() {
        expect(scope.first).toBe('3');
        expect(scope.second).toBe('2');
        expect(scope.result).toBeUndefined();
        scope.subtractNumbers();
        expect(scope.result).toBe(1);
    });

    it('should add two numbers', function() {
        expect(scope.result).toBeUndefined();
        scope.first = '1';
        scope.second = '2';
        scope.addNumbers();
        expect(scope.result).toBe(3);
    });


    it('should subtract two numbers', function() {
        expect(scope.result).toBeUndefined();
        scope.first = '3';
        scope.second = '1';
        scope.subtractNumbers();
        expect(scope.result).toBe(2);
    });

  });

});