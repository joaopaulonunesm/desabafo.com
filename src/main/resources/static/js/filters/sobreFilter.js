angular.module("desabafoApp").filter("sobre", function (){
    return function (input){

        if(input == null){ return null; }

        var output = '- Sobre #' + input;

        return output;
    }
});