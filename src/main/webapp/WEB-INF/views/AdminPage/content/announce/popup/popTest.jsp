<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Layer Popup Example</title>
</head>
<body>

<button id="openPopup">Open Popup</button>

<div id="popupContainer" class="popup-container">
  <div class="popup-content">
    <span class="close" id="closePopup">&times;</span>
    <h2>Layer Popup</h2>
    <p>This is a layer popup example.</p>
  </div>
</div>

<script  type="text/javascript">
//Get the popup container and the button that opens it
const popupContainer = document.getElementById("popupContainer");
const openButton = document.getElementById("openPopup");

// When the user clicks on the button, open the popup
openButton.addEventListener("click", function() {
  popupContainer.style.display = "block";
});

// When the user clicks on <span> (x), close the popup
document.getElementById("closePopup").addEventListener("click", function() {
  popupContainer.style.display = "none";
});

// When the user clicks anywhere outside of the popup, close it
window.addEventListener("click", function(event) {
  if (event.target == popupContainer) {
    popupContainer.style.display = "none";
  }
});

</script>
</body>
</html>