//Find the longest string in a string array

document.getElementById("submit-btn for prob1").addEventListener("click", findLongestString);

function findLongestString(){
    let input = document.getElementById("test array for prob1").value;
    let inputArr = input.split(',');
    console.log(inputArr)
    var size = inputArr.length;
    var longestStr = inputArr[0];
    for(var i=1; i<size; i++){
        if(longestStr.length < inputArr[i].length){
            longestStr = inputArr[i];
        }
    }
    document.getElementById("prob1").innerHTML = "the longest string: " + longestStr;
}



//prob2 reverse array
document.getElementById("submit-btn for prob2").addEventListener("click", reverseArray);

function reverseArray(){
    var input = document.getElementById("test array for prob2").value;
    let inputArr = input.split(', ');
    console.log(inputArr);
    var size = inputArr.length;
    var reversedArr = new Array();
    for(var i = size-1; i>= 0; i--){
        reversedArr[size-1-i] = inputArr[i];
        console.log(reversedArr[size-1-i]);
    }
    document.getElementById("prob2").innerHTML ="the reversed array:" + reversedArr;
}




//prob3: count vowels
document.getElementById("submit-btn for prob3").addEventListener("click", countVowels)


function countVowels(){
    var inputStr = document.getElementById("test string for prob3").value;
    size = inputStr.length;
    var count = 0;
    var vowels = ["a","e","i","o","u"];
    for(var i = 0; i< size; i++){
        if(vowels.includes(inputStr.charAt(i))){
            count++;
        }
    }
    document.getElementById("prob3").innerHTML = "the number of vowels:" + count;
}


//prob4
//var inputEmail = "@you.me.net ";// invalid emails:mysite@.com.my; @you.me.net
document.getElementById("submit-btn for prob4").addEventListener("click", validEmail);
function validEmail(){
    var inputEmail = document.getElementById("test email for prob4").value;
    var mailFormat =  /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    
        if (mailFormat.test(inputEmail))
        {
            document.getElementById("prob4"). innerHTML = "The input email is valid";
        }
        else{
            document.getElementById("prob4").innerHTML = "the input email is invalid!";
        }
}



//prob5 remove character
document.getElementById("submit-btn for prob5").addEventListener("click", removeCharacter);

function removeCharacter(){
    var inputStr = document.getElementById("test string for prob5").value;
    var index = parseInt(document.getElementById("the index of character").value, 10);
    var size = inputStr.length;
    if(index == 0){
        document.getElementById("prob5").innerHTML = inputStr.slice(1);
    }
    else if (index == size-1){
        document.getElementById("prob5").innerHTML = inputStr.slice(0, size-1);
    }
    else if (index > 0 && index < size-1){
        let rightStr = inputStr.substring(index+1, size);
        let leftStr = inputStr.substring(0, index);
        console.log(leftStr);
        console.log(rightStr);
        document.getElementById("prob5").innerHTML = leftStr + rightStr;
    }
    else document.getElementById("prob5").innerHTML = "Invalid index!";
}



// problem 6 Bubble Sort 

//var numArr = [9, 11, 4, 7, 22, -1, 28, 63, 126];
document.getElementById("submit-btn for prob6").addEventListener("click", bubbleSortAscending);

function bubbleSortAscending(){
    var numStr = document.getElementById("test array for prob6").value.split(',');
    var size = numStr.length;
    console.log(numStr);
    var nums =[];
    for(var i = 0; i <size; i++){
        nums.push(parseInt(numStr[i], 10));
    }
    console.log(nums);
    for(var i = 0; i < size; i++){
        for(var j = 0; j < size - i; j++){
            if(nums[j] > nums[j+1]){
                var temp = nums[j];
                nums[j] = nums[j+1];
                nums[j+1] = temp;
            }

        }
    }
    document.getElementById("prob6").innerHTML = "the sorted array with ascending order" + nums;
}



//prob 7 even number

var testNum = 35;
document.getElementById("test number for prob7").innerHTML = "Test number: " + testNum;

function isEven(num){
    var isEven = true;
    if(num & 1){
        isEven = false;
    }
    return isEven;
}
document.getElementById("prob7").innerHTML = isEven(testNum);

//prob 8 Panlindrome
var testStr = "revature"; //panlinedrome test: redivider
document.getElementById("test string for prob8").innerHTML = "test string: " + testStr;
function isPalindrome(str){
    var isPalindrome = true;
    var size = str.length;
    var i = 0; var j = size -1;
    while(i<j){
        if(str[i] != str[j]){
            isPalindrome = false;
            break;
        }
        i++;
        j--;
    }
    
    return isPalindrome;
}
document.getElementById("prob8").innerHTML = "is Palindrome? " + isPalindrome(testStr);

//prob 9 is leap year
var inputTime = new Date(2018, 5);
console.log(inputTime.getFullYear());
document.getElementById("test year for prob9").innerHTML = "test year: " + inputTime.getFullYear();
function isLeapYear(inputDate){
     var testYear = inputDate.getFullYear();
     var isLeapYear = false;
     if((testYear%4 == 0 && testYear%100 != 0) || testYear%400 == 0){
        isLeapYear = true;
     }
     return isLeapYear;
}
document.getElementById("prob9").innerHTML = isLeapYear(inputTime);

//prob 10 Shapes
document.getElementById("test shape").innerHTML = "Square 5 %";

function printShape(shape, height, character){
    switch(shape){
        case "Square":
            for(var i = 0; i< height; i++){
                console.log(character.repeat(height));
            }
            break;
        case "Triangle":
            for(var i = 1; i <= height; i++){
                console.log(character.repeat(i));
            }
            break;
        case "Diamond":
            if(height%2 == 0){
               console.log("the input height must be odd!");
               
            }
            else {
                for(var i = 1; i <= height; i++){
                    if(i <= Math.ceil(height/2)){
                        console.log(' '.repeat((height-2*i+1)/2) + character.repeat(2*i-1) + ' '.repeat((height-2*i+1)/2) );
                    }
                    else{
                        console.log(' '.repeat((2*i- height -1)/2) + character.repeat(2*(height-i)+1) + ' '.repeat((2*i- height -1)/2) );
                    }
                }
            }
            break;
        default:
            console.log("please input a valid shape!");

    }
}

printShape("Square", 5, '%');

//prob 11 rotate left
var inputArr = [1,2,3,4,5,6];
var num = 5;
document.getElementById("test array for prob11").innerHTML = "Test Array: " + inputArr + " rotate number: " + num; 
function rotateLeft(arr, n){
    var size = arr.length;
    var resultArr = [];
    if(n == 0 || n%size == 0){
        resultArr = arr;
    }
    else {
        resultArr = arr.slice(n%size).concat(arr.slice(0,n%size));
    }
    return resultArr;
}

document.getElementById("prob11").innerHTML = rotateLeft(inputArr, num);

//prob 12 balanced brackets

var bracketStr = "(2%*[Hi)]";// balance string: "we(us{[37&]})";
document.getElementById("test string for prob12").innerHTML = "test string: " + bracketStr;

function balanced(str){
    var size = str.length;
    if(size == 0){
        return true;
    }
    var bracketArr = [];
    var leftBracket = ['(','{', '['];
    var rightBracket =[')', '}', ']'];
    var isBalanced = true;
    for(var i = 0; i < size; i++){
        var temp = str.charAt(i);
        if(leftBracket.indexOf(temp) != -1 ){
            bracketArr.push(temp);
        }
        else if (rightBracket.indexOf(temp) != -1){
            if(bracketArr.length == 0){
                isBalanced = false;
            }
            switch(temp){
                case ')':
                    if (bracketArr.pop() !== '('){
                        isBalanced = false;
                    }
                    break;
                case '}':
                    if(bracketArr.pop() !== '{'){
                        isBalanced = false;
                    }
                    break;
                case ']':
                    if(bracketArr.pop() !== '['){
                        isBalanced = false;
                    }
                    break;
                default :
                    continue;
            }

        }
        else continue;
    }
    if(bracketArr.length != 0){
        isBalanced = false;
    }
    return isBalanced;
}

document.getElementById("prob12").innerHTML = "is balanced? " + balanced(bracketStr);
