/**
 * TODO: comment
 * Created by vlad on 14.04.16.
 */

function validateForm() {
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
        var testMessage = "Incorrect input values: " + errorValues + " !";
        var message = document.getElementById("message");
        message.innerHTML = testMessage;
        message.setAttribute("style", "color: red");
        // alert(testMessage);

        return false;
    }

    return true;
}
