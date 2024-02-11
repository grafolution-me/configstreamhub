import { Injectable } from '@angular/core';
import { Store, select } from '@ngrx/store';
import { Observable } from 'rxjs';
import {CommentActions} from "../../store/comment/comment.actions";
import * as CommentSelectors from "../../store/comment/comment.selectors";
import {MainStore} from "../../store/main.store";
@Injectable({
  providedIn: 'root'
})
export class CommentService {
  constructor(private store: Store<MainStore>) {}

  getComments(): Observable<Comment[]> {
    return this.store.pipe(select(CommentSelectors.selectAllComments));
  }

  addComment(comment: Comment): void {
    this.store.dispatch(CommentActions.addComment({ comment }));
  }

  updateComment(comment: Comment): void {
    this.store.dispatch(CommentActions.updateComment({ comment }));
  }

  deleteComment(id: string): void {
    this.store.dispatch(CommentActions.deleteComment({ id }));
  }
}
