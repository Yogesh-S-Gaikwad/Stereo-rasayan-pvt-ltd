document.addEventListener("DOMContentLoaded", function () {
    const addProductBtn = document.querySelector(".addProductBtn");
    const addProductDiv = document.querySelector(".addProduct");
    addProductDiv.style.display = "none";


    addProductBtn.addEventListener("click", function () {
      if (addProductDiv.style.display === "none") {
        addProductDiv.style.display = "block";
      } else {
        addProductDiv.style.display = "none";
      }
    });
  });
  
 