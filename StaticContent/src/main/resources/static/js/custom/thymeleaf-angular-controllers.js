var thymeleafAngularControllers = angular.module('thymeleafAngularControllers', []);

thymeleafAngularControllers.controller('DemoCtrl', ['$scope', '$window', function ($scope, $window) {
    $scope.first = $window.defaultFirst;
    $scope.second = $window.defaultSecond;
    $scope.addNumbers = function() {
            var a = Number($scope.first || 0);
            var b = Number($scope.second || 0);
            $scope.result = a+b;
    }

    $scope.subtractNumbers = function() {
            var a = Number($scope.first || 0);
            var b = Number($scope.second || 0);
            $scope.result = a-b;
    }
}]);