/*
Assignment No.: 2(Group C)
Assignment Name: Write a program to Smart Watch App Development with Tizen. Objective of this assignment is to design simple comic app with the Tizen SDK for Wearable and run it on the smart watch emulator that comes bundled with the IDE.
Class: BE			Div: B
Roll No.:59		Batch: B3
*/

window.onload = function () {
    // TODO:: Do your initialization job

    // add eventListener for tizenhwkey
    document.addEventListener('tizenhwkey', function(e) {
        if(e.keyName == "back")
            tizen.application.getCurrentApplication().exit();
    });

    // Sample code
    var textbox = document.querySelector('.contents');
    textbox.addEventListener("click", function(){
    	box = document.querySelector('#textbox');
    	box.innerHTML = box.innerHTML == "Sandip" ? "Foundation" : "Sandip";
    });
    
};
