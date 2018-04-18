function showHide(id) {
    let forma = document.getElementById('smartphone' + id);

    if (forma.style.display !== "none") forma.style.display = "none";
    else forma.style.display = "table";
}
