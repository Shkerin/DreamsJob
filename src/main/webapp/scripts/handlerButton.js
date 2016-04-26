/**
 * TODO: comment
 * Created by vlad on 14.04.16.
 */

window.onload = function () {
    var divMessage = document.getElementById("message");

    function setAttributesForTag(tag, text) {
        tag.innerHTML = text;
        tag.setAttribute("style", "color: red; text-align: center; margin-top: 10px;");
    }

    if (divMessage.innerHTML !== "") {
        setAttributesForTag(divMessage, divMessage.innerHTML);
    }

    document.getElementById("buttonSave").onclick = function () {
        setAttributesForTag(divMessage, "The changes are saved.");
    };

    document.getElementById("buttonDelete").onclick = function () {
        setAttributesForTag(divMessage, "Deleted user.");
    };
};
