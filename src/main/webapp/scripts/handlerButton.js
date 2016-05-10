/**
 * Form validation.
 *
 * @author Vladimir Shkerin
 * @since 26.04.2016
 */

function validateFormUser() {
    var fieldValues = [
        {name: "Id", value: document.getElementsByName("id")[0].value},
        {name: "Name", value: document.getElementsByName("name")[0].value},
        {name: "Growth", value: document.getElementsByName("growth")[0].value},
        {name: "Birth Day", value: document.getElementsByName("birthDay")[0].value},
        {name: "Email", value: document.getElementsByName("email")[0].value}
    ];

    var errorValues = [];
    for (var i = 0; i < fieldValues.length; i++) {
        if (fieldValues[i].value.trim() === "") {
            errorValues[errorValues.length] = fieldValues[i].name;
        } else if (fieldValues[i].name === "Growth") {
            if (fieldValues[i].value < 1 || fieldValues[i] > 200) {
                errorValues[errorValues.length] = fieldValues[i].name;
            }
        }
    }

    if (errorValues.length > 0) {
        outputMessage(errorValues);
        return false;
    }

    return true;
}

function validateFormItem() {
    var fieldValues = [
        {name: "Id", value: document.getElementsByName("id")[0].value},
        {name: "Name", value: document.getElementsByName("name")[0].value},
        {name: "Desc", value: document.getElementsByName("desc")[0].value}
    ];

    var errorValues = [];
    for (var i = 0; i < fieldValues.length; i++) {
        if (fieldValues[i].value.trim() === "") {
            errorValues[errorValues.length] = fieldValues[i].name;
        }
    }

    if (errorValues.length > 0) {
        outputMessage(errorValues);
        return false;
    }

    return true;
}

function outputMessage(errorValues) {
    var testMessage = "Incorrect input values: " + errorValues + " !";
    var message = document.getElementById("message");
    message.innerHTML = testMessage;
    message.setAttribute("style", "color: red");
    // alert(testMessage);
}