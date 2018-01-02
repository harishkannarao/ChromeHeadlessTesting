'use strict';

/* App Module */
var myAngularApp = angular.module('myAngularApp', [
  'ngRoute',
  'myAngularAppControllers'
]);

myAngularApp.config(['$routeProvider', function($routeProvider) {
    $routeProvider.
      when('/home', {
        templateUrl: 'partials/home-page.html',
        controller: 'HomeCtrl'
      }).
      otherwise({
        redirectTo: '/home'
      });
}]);