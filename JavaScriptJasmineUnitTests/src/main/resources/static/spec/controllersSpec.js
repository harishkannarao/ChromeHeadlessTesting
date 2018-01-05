describe('myAngularApp controllers', function() {

  describe('HomeCtrl', function(){
    var scope, ctrl;

    // Load our app module definition before each test.
    beforeEach(module('myAngularApp'));

    // The injector ignores leading and trailing underscores here (i.e. _$httpBackend_).
    // This allows us to inject a service but then attach it to a variable
    // with the same name as the service in order to avoid a name conflict.
    beforeEach(inject(function($rootScope, $controller) {
      scope = $rootScope.$new();
      ctrl = $controller('HomeCtrl', {$scope: scope});
    }));

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