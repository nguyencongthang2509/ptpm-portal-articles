var Summernote = {};
Summernote.summer = function () {
  $("#summernote").summernote({
    placeholder: "What are you writing today?",
    tabsize: 2,
    height: 600,
    toolbar: [
      ["style", ["style"]],
      ["font", ["bold", "underline", "clear"]],
      ["color", ["color"]],
      ["para", ["ul", "ol", "paragraph"]],
      ["table", ["table"]],
      ["insert", ["link", "picture", "video"]],
      ["view", ["fullscreen", "codeview", "help"]],
    ],
  });
  
};
