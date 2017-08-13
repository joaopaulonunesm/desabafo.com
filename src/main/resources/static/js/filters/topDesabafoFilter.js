angular.module("desabafoApp").filter("reticencia", function (){
    return function (input, size){

        if(input == null){ return null; }
        
        if(input.length <= size){ return input; }

    	var output = input.substring(0,size) + "...";
    	return output;
    }
});