import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArticleItem } from './article-item';

describe('ArticleItem', () => {
  let component: ArticleItem;
  let fixture: ComponentFixture<ArticleItem>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ArticleItem]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArticleItem);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
