var fileX = {
    "docs": [

    ]
}
var app = angular.module('projectFront',[]);

app.controller('documents', function($scope) {
    $scope.documents = fileX.docs;
});