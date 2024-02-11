import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, concatMap } from 'rxjs/operators';
import { Observable, EMPTY, of } from 'rxjs';
import { CommentActions } from './comment.actions';


@Injectable()
export class CommentEffects {

  commentComments$ = createEffect(() => {
    return this.actions$.pipe(

      ofType(CommentActions.commentComments),
      concatMap(() =>
        /** An EMPTY observable only emits completion. Replace with your own observable API request */
        EMPTY.pipe(
          map(data => CommentActions.commentCommentsSuccess({ data })),
          catchError(error => of(CommentActions.commentCommentsFailure({ error }))))
      )
    );
  });


  constructor(private actions$: Actions) {}
}
