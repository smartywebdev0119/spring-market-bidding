<template>
  <form id="create-auction" @submit.prevent="submitForm">
    
      <label for="files" class="font-italic file-btn">Upload images</label>
      <input   
        type="file"
        id="files"
        accept=".png,.jpg,.jpeg,.gif,.bmp,.jfif"
        multiple
        required
        @change="filesChange($event.target.files)"
        hidden
      />
  </form>
</template>

<script>
import { Vue } from "vue-property-decorator";
import { create_UUID } from "../core/GenerateUUID";
export default class ImageUploader extends Vue {
  imgObj = {
    images: [],
    imageFiles: null,
    smallImages: []
  };

  smallImages = [];

  filesChange(fileList) {
    if (!fileList.length) return;

    const formData = new FormData();
    this.imgObj.images = [];
    this.imgObj.smallImages = [];

    Array.from(Array(fileList.length).keys()).map((x) => {

      const uuid = create_UUID();
      let fileExt = fileList[x].name;

      this.imgObj.smallImages.push(URL.createObjectURL(fileList[x]))

      fileExt = fileExt.slice(fileExt.lastIndexOf("."));
      const filename = uuid + fileExt;

      this.imgObj.images.push("/uploads/" + filename);
      formData.append("files", fileList[x], filename);
    });
    this.imgObj.imageFiles = formData;
    this.$emit("chosen-img", this.imgObj);
  }
}
</script>

<style>
.file-btn{
  color: white;
  background-color: teal !important;
  padding: 0.5em;
  
  border-radius: 5px;
  text-align: center;
  
}

#create-auction{
  display: flex;
  align-items: center;
}
</style>
