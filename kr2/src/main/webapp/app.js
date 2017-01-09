angular.module('app', ['ui.router', 'ui.bootstrap']);

angular.element(document).ready(function () {
    angular.bootstrap(document.getElementById('app'), ['app']);
});

angular.module('app')
.directive('convertToNumber', function() {
  return {
    require: 'ngModel',
    link: function(scope, element, attrs, ngModel) {
      ngModel.$parsers.push(function(val) {
        return parseInt(val, 10);
      });
      ngModel.$formatters.push(function(val) {
        return '' + val;
      });
    }
  };
})
.constant('CRITERIA', [
    {title: 'All Students', path: '/'},
    {title: 'Best of 2 Course', path: '/best'},
    {title: 'Good Learning Foreign Students', path: '/good'}
])
.constant('HEADERS', [{
        title: 'First Name',
        field: 'firstName'
    }, {
        title: 'Last Name',
        field: 'lastName'
    }, {
        title: 'Middle Name',
        field: 'middleName'
    }, {
        title: 'Course',
        field: 'course'
    }, {
        title: 'Gender',
        field: 'gender'
    }, {
        title: 'Identity ID',
        field: 'identity'
    }, {
        title: 'Country',
        field: 'country'
    }, {
        title: 'Avg Score',
        field: 'score'
    }
])
.component('sortingCriteria', {
    templateUrl: 'view/sorting-criteria.html',
    controller: 'SortingCriteriaController'
})
.filter('toLowerCase', function toLowerCaseFilterProvider() {
    return function toLowerCaseFilter(value) {
        return ('' + value).toLowerCase();
    }
})
.controller('SortingCriteriaController', function SortingCriteriaController(CRITERIA, $rootScope) {
    this.criteria = CRITERIA;
    this.selected = CRITERIA[0];

    this.onCriteriaChange = function onCriteriaChange(selected) {
        $rootScope.$broadcast('criteria-selected', {path: selected.path});
    }
})
.controller('HomeController', function HomeController(HEADERS, $http, $scope, $uibModal) {
    var vm = this;

    vm.path = '/';
    vm.students = [];
    vm.headers = HEADERS;

    vm.addStudent = addStudent;
    vm.deleteStudent = deleteStudent;
    vm.changeScore = changeScore;

    $scope.$on('criteria-selected', onCriteriaChange)

    getStudents();

    function addStudent(student) {
        var modal = $uibModal.open({
            templateUrl: 'view/student.html',
            controller: function($scope, $uibModalInstance, student, countries, $http) {
                $scope.student = student;
                $scope.student.gender = $scope.student.gender || 0;
                $scope.student.country = $scope.student.country || countries[0];
                $scope.countries = countries;
                $scope.type = student ? 'Edit' : 'Add';

                $scope.submit = submit;
                $scope.close = close;

                function submit() {
                    $http.post('/students/', $scope.student)
                        .then(function(response) {
                            $uibModalInstance.close(response.data);
                        });
                }

                function close() {
                    $uibModalInstance.dismiss();
                }
            },
            resolve: {
                student: function() {
                    return student || {};
                },
                countries: function($http) {
                    return $http.get('/countries/').then(function(response) {
                        return response.data;
                    });
                }
            }
        });

        modal.result.then(function() {
            getStudents();
        });
    }

    function changeScore(student) {
        var modal = $uibModal.open({
            templateUrl: 'view/scores.html',
            controller: function($scope, $uibModalInstance, student, subjects, $http) {
                $scope.student = student;
                $scope.subjects = subjects;

                $scope.mark = {
                    student: student
                };
                $scope.marks = student.marks;
                $scope.create = create;
                $scope.close = close;
                $scope.removeScore = removeScore;

                function create() {
                    $http.post('/marks/', $scope.mark)
                        .then(function(response) {
                            $scope.mark.subject = null;
                            $scope.mark.mark = undefined;
                            return $http.get('/students/' + student.id)
                        })
                        .then((response) => {
                            $scope.marks = response.data.marks;
                        });
                }

                function removeScore(mark) {
                    $http.delete('/marks/' + mark.id)
                        .then((response) => {
                            const index = $scope.marks.indexOf(mark);
                            if (index !== -1) {
                                $scope.marks.splice(index, 1);
                            }
                        });
                }

                function close() {
                    $uibModalInstance.dismiss();
                }
            },
            resolve: {
                student: function() {
                    return student || {};
                },
                subjects: function($http) {
                    return $http.get('/subjects/').then(function(response) {
                        return response.data;
                    });
                }
            }
        });

        modal.result.then(getStudents, getStudents);
    }

    function deleteStudent(student) {
        $http.delete('/students/' + student.id)
            .then(studentDeleted)
            .catch(serverError);

        function studentDeleted(response) {
            getStudents();
        }
    }

    function getStudents() {
        $http.get('/students' + vm.path)
            .then(gotStudents)
            .catch(serverError);

        function gotStudents(response) {
            vm.students = response.data;
        }
    }

    function serverError(reason) {
        console.error(reason);
    }

    function onCriteriaChange(event, data) {
        vm.path = data.path;
        getStudents();
    }
})
.config(function($stateProvider, $locationProvider, $urlRouterProvider) {
    $stateProvider
        .state('home', {
            url: '/',
            views: {
                "": {
                    templateUrl: 'view/home.html',
                    controller: 'HomeController as home'
                },
                "navbar": {
                    templateUrl: 'view/navbar.html'
                }
            }
        });

    $urlRouterProvider.otherwise('/');

    $locationProvider.html5Mode(true);
});
