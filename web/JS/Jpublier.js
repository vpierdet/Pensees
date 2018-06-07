
function checkEmpty(){
    var text = document.getElementsByName("user_message").value;
    if (text.empty() || text == "Ecrivez votre message ici..."){
        alert("Votre message est vide.")
        return false;
    }
    else return true;
}
