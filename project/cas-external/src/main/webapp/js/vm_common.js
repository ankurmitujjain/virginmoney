//moves the element to the next Login screen field
//if all characters are filled
function autoTabLoginPage(element)
{

    elementId = element.id;

    element1 = 'dobDay';
    element2 = 'dobMonth';
    element3 = 'dobYear';
    nextbtn  = 'next-btn';

    pwdelement1 = 'passwordCharacter1';
    pwdelement2 = 'passwordCharacter2';
    loginbtn    = 'login-btn';

    autoTab = false;

    if ((elementId == element1 || elementId == element2) && document.getElementById(elementId).value.length == 2) {
        autoTab = true;
    }
    else if (elementId == element3 && document.getElementById(elementId).value.length == 4) {
        autoTab = true;
    }
    else if ((elementId == pwdelement1 || elementId == pwdelement2) && document.getElementById(elementId).value.length == 1) {
        autoTab = true;
    }

    if (autoTab) {

        if (elementId == element1)
        {
            document.getElementById(element2).focus();
        } else if (elementId == element2) {
            document.getElementById(element3).focus();
        } else if (elementId == element3) {
            document.getElementById(nextbtn).focus();
        } else if (elementId == pwdelement1) {
            document.getElementById(pwdelement2).focus();
        } else if (elementId == pwdelement2) {
            document.getElementById(loginbtn).focus();
        }

    }
}
