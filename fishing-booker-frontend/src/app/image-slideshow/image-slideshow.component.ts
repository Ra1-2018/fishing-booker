import { Component, Input, OnInit } from '@angular/core';
import { ImageSlideshowService } from './image-slideshow.service';

@Component({
  selector: 'app-image-slideshow',
  templateUrl: './image-slideshow.component.html',
  styleUrls: ['./image-slideshow.component.css']
})
export class ImageSlideshowComponent implements OnInit {

  @Input() images: any[] = [];
  imageIndex: number = 0;
  @Input() isOwner: boolean = false;
  @Input() serviceId: number = 0;

  constructor(private imageSlideshowService: ImageSlideshowService) { }

  ngOnInit(): void {
  }

  prevSlide() {
    this.imageIndex --;
  }

  nextSlide() {
    this.imageIndex ++;
  }

  currentSlide(index: number) {
    this.imageIndex = index;
  }


  deleteImage(){
    this.imageSlideshowService.deleteImage(this.images[this.imageIndex].id).subscribe({
      next: () => {
        this.imageSlideshowService.getImages(this.serviceId).subscribe({
          next: images => this.images = images, 
          error: (err) => {}
        }); 
      },
      error: (err) => {alert("Error has occured, image was not deleted!")}
    })
  }

}
