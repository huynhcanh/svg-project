function handleShowFullImg(itemImg, idModal, idImgMyModal, classCloseMyModal){
    // Get the modal
    var modal = document.getElementById(idModal);
// Get the image and insert it inside the modal - use its "alt" text as a caption
    var modalImg = document.getElementById(idImgMyModal);

    modal.style.display = "block";
    modalImg.src = itemImg.src;

// Get the <span> element that closes the modal
    var span = document.getElementsByClassName(classCloseMyModal)[0];

// When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        modal.style.display = "none";
    }
}
