/*************************************************************
 *
 * javascript functions for password strength & validation
 *
 * ***********************************************************
 *
 * FILE HISTORY
 *
 * Version  Date       User       Description
 * ======== ========== ========== ============================================================
 * 1.0      17/06/2008 TalbotK    Initial creation
 * 1.1      16/04/2009 TalbotK    Change the colour range used
 *
 * ************************************************************************************************
 *
 * Global variables for these functions.
 *
 * Calculation for score:
 *
 * Ue = Uppercase char exists
 * Le = Lowercase char exists
 * Ne = Number char exists
 * Se = Symbol char exists
 *
 * var = Variety factor
 *
 * Uc = Uppercase char count
 * Lc = Lowercase char count
 * Nc = Number char count
 * Sc = Symbol char count
 *
 * Uf = Uppercase factor
 * Lf = Lowercase factor
 * Nf = Number factor
 * Sf = Symbol factor
 *
 * Score = [((Ue + Le + Ne + Se) x var)]+[(Uc x Uf) + (Lc x Lf) + (Nc x Nf) + (Sc x Sf)]+[Length bonus]
 *
 * ************************************************************************************************
 *
 * Characters allowed: (From
 * http://www.htmlgoodies.com/beyond/javascript/article.php/3471141)
 *
 * 48 - 57 = all the numbers
 * 65 - 90 = all uppercase
 * 97 - 122 = all lowercase
 * 33 = !
 * 35 - 38 = # $ % &
 * 40 - 43 = ( ) * +
 * 63 - 64 = ? @
 * 8 = backspace
 * 46 = del
 *
 * Other rules: Password cannot contain any 4 consecutive chars from the
 * username Password cannot contain more than 2 consecutive characters the same
 *
 * method : validatePasswordComplexity()
 *
 *
 ***************************************************************************************************/


var passChars = new Array(); // holds each of the password's chars

// variables for password rules
var max_score = 120; // maximum score of a password
var min_pass_len = 8; // minimum length of a password
var min_ok_score = 48; // minimum score before allowed
var weak_limit = 55; // password is weak below this score
var med_limit = 85; // password is medium below this score
var max_usr_char = 4; // maximum common chars between username and password. ie. 3 are allowed, 4 aren't
var max_repeat = 2; // maximum number of consecutive chars the same. ie. 2 are allowed, 3 aren't

// multiplication factors for each type of character
var lower_fact = 1;
var upper_fact = 1;
var number_fact = 3;
var symbol_fact = 4;
var variety_fact = 20;

// intermediate colour scores
var col_orange = 66;
var col_yellow = 84;
var col_lime   = 102


/*******************************************************************************
 * Function to convert the password string into an array of ASCII values
 ******************************************************************************/

function ascii_value(c) {
   // loop through all possible ASCII values
   for (i = 0; i < 256; i++) {
      // convert i into a 2-digit hex string
      var h = i.toString(16);
      if (h.length == 1) {
         h = "0" + h;
      }

      // insert a % character into the string
      h = "%" + h;

      // determine the character represented by the escape code
      h = unescape(h);

      // if the characters match, we've found the ASCII value
      if (h == c) {
         break;
      }
   }
   return i;
}

/*******************************************************************************
 * Function to create a score for the password based on its strength
 ******************************************************************************/

function validatePassword(pass) {

   // character types that exist in password
   var lower_exist = 0;
   var upper_exist = 0;
   var number_exist = 0;
   var symbol_exist = 0;

   // number of each type of character
   var no_lower = 0;
   var no_upper = 0;
   var no_number = 0;
   var no_symbol = 0;

   // Object to return
   score = new Object();
   score.numeric = 0;
   score.desc = "Weak";
   score.valid1 = true;
   score.valid2 = false;
   score.reason = "";

   // Re-assign the password characters to the array for processing
   for ( var i = 0; i < pass.length; i++) {
      passChars[i] = ascii_value(pass.substring(i, i + 1));
   }

   // check for different character types and how many of each
   for ( var j = 0; j < pass.length; j++) {
      if (passChars[j] >= 65 && passChars[j] <= 90) {
         upper_exist = 1;
         no_upper += 1;
      } else if (passChars[j] >= 97 && passChars[j] <= 122) {
         lower_exist = 1;
         no_lower += 1;
      } else if (passChars[j] >= 48 && passChars[j] <= 57) {
         number_exist = 1;
         no_number += 1;
      } else {
         symbol_exist = 1;
         no_symbol += 1;
      }
   }

   // determine any length bonus
   if (pass.length == 9) {
      length_bonus = 4;
   } else if (pass.length > 9) {
      length_bonus = 8;
   } else {
      length_bonus = 0;
   }

   score.numeric = ((upper_exist + lower_exist + number_exist + symbol_exist) * variety_fact); // total up for variety
   score.numeric += ((no_upper * upper_fact) + (no_lower * lower_fact) + (no_number * number_fact) + (no_symbol * symbol_fact)); // add some for more variety
   score.numeric += length_bonus; // add some for length

   // determine the strength description
   if (score.numeric <= weak_limit) {
      score.desc = "Weak";
   } else if (score.numeric > weak_limit && score.numeric < med_limit) {
      score.desc = "Medium";
   } else {
      score.desc = "Strong";
   }

   // Check for consecutive chars
   for ( var k = 1; k < (pass.length - max_repeat); k++) {
      sameCount = 0;
      for ( var m = 0; m < max_repeat; m++) {
         if (passChars[k + m] == passChars[k + m + 1]) {
            sameCount += 1;
         }
      }
      if (sameCount > (max_repeat - 1)) {
         score.valid1 = false;
      }
   }

   // Check for more than one kind of char
   if (upper_exist + lower_exist + number_exist + symbol_exist > 1) {
      score.valid2 = true;
   }
   return score;
}

/*******************************************************************************
 * Function to create a colour between red and green based on the score
 ******************************************************************************/

function createStrengthColour(passScore) {

   // make sure score is less than max_score.
   if (passScore >= max_score) {
      passScore = max_score;
   }


   /* Logic to determine colour has changed - v1.1

   // rough conversion factor between max_score and 255
   var convert = (255 / max_score);

   // Convert decimal to hex
   function d2h(Decimal) {

      var hexChars = "0123456789ABCDEF";
      var a = Decimal / 16;
      var b = Decimal - (a * 16);

      charA = hexChars.charAt(a);
      charB = hexChars.charAt(b);

      // cater for zeros
      if (!charA) {
         charA = "0";
      }
      if (!charB) {
         charB = "0";
      }

      hex = "" + charA + charB;
      return hex;
   }

   red = d2h(Math.floor(255 - (passScore * convert)));
   green = d2h(Math.floor(passScore * convert));

   colour = "#" + red + green + "00";

   */

   // Determine colour based on password score - v1.1
   if (passScore < min_ok_score) {
      colour = "#FF0000";
   }else if (passScore < col_orange) {
      colour = "#FF9900";
   }else if (passScore < col_yellow) {
      colour = "#FFFF00";
   }else if (passScore < col_lime) {
      colour = "#99FF00";
   }else{
      colour = "#00FF00";
   }

   return colour;
}

/*******************************************************************************
 * Function validate a username, password and confirm password values
 *
 * @TODO This function is not using now after the confirmation we are not
 *       diaplying the popup we need to remove this code.
 *
 ******************************************************************************/

/*function validatePassPage(pass1, pass2) {

   var result = true;

   score = validatePassword(pass1);

   // check the valid1 property of score to see if there were too many
   // consecutive chars
   if (!score.valid1) {
      score.reason += "* Password has more than the allowed consecutive same characters.\n";
      result = false;   }

   // check the valid2 property of the score to see if enough different chars
   // were used
   if (!score.valid2) {
      score.reason += "* Password does not contain enough different types of character.\n";
      result = false;
   }

   // check the password score is above the minimum acceptable value
   if (score.numeric < min_ok_score) {
      score.reason += "* Password has not reached the minimum score.\n";
      result = false;
   }

   // check the password is greater than the minimum length
   if (pass1.length < min_pass_len) {
      score.reason += "* Password is not long enough.\n";
      result = false;
   }

   // check passwords match
   if (pass1 != pass2) {
      score.reason += "* Passwords do not match.\n";
      result = false;
   }

   return result;
}*/

/*
 * method: validatePasswordComplexity() @description : used to show dynamic
 * password strength color
 */
function validatePasswordComplexity() {

   var password = document.getElementById("password").value;
   // TODO currently we are not going to display the validation message using
   // java script so comment validatePassword method
   score = validatePassword(password);
   colour = createStrengthColour(score.numeric);
   document.getElementById("result_box").style.backgroundColor = colour;

}
