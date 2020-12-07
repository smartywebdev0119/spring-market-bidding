<template>
  <form id="create-auction" @submit.prevent="submitForm">
    <div>
      <label for="files" class="text-primary font-italic">Images to upload:</label>
      <input
        type="file"
        name="files"
        accept=".png,.jpg,.jpeg,.gif,.bmp,.jfif"
        multiple
        required
        @change="filesChange($event.target.files)"
      />
    </div>
  </form>
</template>

<script>
import { Vue } from "vue-property-decorator";
import { create_UUID } from "../core/GenerateUUID";
export default class ImageUploader extends Vue {
  imgObj = {
    images: [],
    imageFiles: null,
  };

  filesChange(fileList) {
    if (!fileList.length) return;

    // let uuids = [
    //   {name: ""},
    //   {name: ""},
    //   {name: ""},
    // ];
    // for(let uuid of uuids ){
    //   uuid.name = create_UUID()
    // }
    // console.log(uuids)

    const formData = new FormData();
    this.imgObj.images = [];

    Array.from(Array(fileList.length).keys()).map((x) => {
      const uuid = create_UUID();

      let fileExt = fileList[x].name;
      fileExt = fileExt.slice(fileExt.lastIndexOf("."));
      const filename = uuid + fileExt;

      this.imgObj.images.push("/uploads/" + filename);
      formData.append("files", fileList[x], filename);
    });

    this.imgObj.imageFiles = formData;
    console.log("HOOOOOORA ", this.imgObj);
    this.$emit("chosen-img", this.imgObj);
  }
}
</script>

<style></style>
