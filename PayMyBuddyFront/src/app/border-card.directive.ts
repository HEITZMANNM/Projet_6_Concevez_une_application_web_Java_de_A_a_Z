import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
  selector: '[homeBorderCard]'
})
export class BorderCardDirective {

private initialColor: string = '#f1ffff';
private defaultColor: string = '#00d3a9';
private defaultHeight: number = 200;

  constructor(private el: ElementRef) {

   this.setHeight(this.defaultHeight);
   this.setBorder(this.initialColor);
   }

 

   @HostListener('mouseenter') onMouseEnter()
   {
   this.setBorder(this.defaultColor);
   }

   @HostListener('mouseleave') onMouseLeave()
   {
   this.setBorder(this.initialColor);
   }

  setHeight(height: number)
  {
  this.el.nativeElement.style.height = height +'px';
  }

setBorder(color: string)
{
this.el.nativeElement.style.border = 'solid 4px' + color;
}



}
