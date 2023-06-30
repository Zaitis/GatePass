import { LitElement, html, css, customElement } from 'lit-element';

@customElement('main-page')
export class MainPage extends LitElement {
  static get styles() {
    return css`
      :host {
          display: block;
          height: 100%;
      }
      `;
  }

  render() {
    return html`
<h1 id="h1">Heading 1</h1>
<div style="width: 100%; height: 100%;">
  Div 
</div>
<span>Span</span>
<a href="https://vaadin.com">Link</a>
<template is="dom-repeat" items="[[items]]">
 <div>
   # [[index]] 
 </div>
 <div>
   Value1: 
  <span>[[item.value1]]</span>
 </div>
 <div>
   Value2: 
  <span>[[item.value2]]</span>
 </div>
</template>
`;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
