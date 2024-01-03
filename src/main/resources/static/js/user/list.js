/** Process when delete button is pressed */
function clickBtnDelete() {
    var idck;

    // Get the checked element
    if (typeof document.frm.idck.length === 'undefined') {
        idck = [{ 'checked': document.frm.idck.checked }];
    } else {
        idck = document.frm.idck;
    }

    // Count the number of elements checked
    var cnt = 0;
    for (i = 0; i < idck.length; i++) {
        if (idck[i].checked) {
            cnt++;
        }
    }

    // Stop processing if User is not selected
    if (cnt == 0) {
        alert('Userが選択されていません。');
        return false;
    }

    // Display confirmation dialog if User is selected
    if (window.confirm(`${cnt}件削除して良いですか？`)) {
         // Execute process when OK is pressed
        return true;
    } else {
        return false;
    }
}

// Assign a function to the delete button
document.getElementById("deleteRun").onclick = clickBtnDelete;