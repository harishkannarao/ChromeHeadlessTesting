var myAngularAppControllers = angular.module('myAngularAppControllers', []);

myAngularAppControllers.controller('HomeCtrl', ['$scope', function ($scope) {
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