import { createFeature, createReducer, on } from '@ngrx/store';
import { EntityState, EntityAdapter, createEntityAdapter } from '@ngrx/entity';
import { Comment } from './comment.model';
import { CommentActions } from './comment.actions';

export const commentsFeatureKey = 'comments';

export interface CommentState extends EntityState<Comment> {
  // additional entities state properties
}

export const adapter: EntityAdapter<Comment> = createEntityAdapter<Comment>();

export const initialState: CommentState = adapter.getInitialState({
  // additional entity state properties
});

export const commentReducer = createReducer(
  initialState,
  on(CommentActions.addComment,
    (state, action) => adapter.addOne(action.comment, state)
  ),
  on(CommentActions.updateComment,
    (state, action) => adapter.updateOne(action.comment, state)
  ),
  on(CommentActions.deleteComment,
    (state, action) => adapter.removeOne(action.id, state)
  ),
  on(CommentActions.loadComments,
    (state, action) => adapter.setAll(action.comments, state)
  ),
  on(CommentActions.clearComments,
    state => adapter.removeAll(state)
  ),
);

export const commentsFeature = createFeature({
  name: commentsFeatureKey,
  reducer: commentReducer,
  extraSelectors: ({ selectCommentsState }) => ({
    ...adapter.getSelectors(selectCommentsState)
  }),
});

// Adapter's selectors
export const {
  selectIds,
  selectEntities,
  selectAllComments ,
  selectTotal,
} = adapter.getSelectors(commentsFeature);
