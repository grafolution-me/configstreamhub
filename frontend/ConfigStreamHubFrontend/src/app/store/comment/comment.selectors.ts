// comment.selectors.ts
import {createFeatureSelector, createSelector} from '@ngrx/store';
import {adapter, commentsFeatureKey, CommentState} from "./comment.reducer";


export const selectCommentState = createFeatureSelector<CommentState>(commentsFeatureKey);

// Verwendung eines Adapters Selektors
export const selectAllComments = createSelector(
    selectCommentState,
    adapter.getSelectors().selectAll
);

// Eigener Selektor, der auf einem Adapter Selektor basiert
export const selectCommentsByUser = createSelector(
    selectAllComments,
    (comments, props) => comments.filter(comment => comment.userId === props.userId)
);
