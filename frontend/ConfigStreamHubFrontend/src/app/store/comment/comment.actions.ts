import { createActionGroup, emptyProps, props } from '@ngrx/store';
import { Update } from '@ngrx/entity';

import { Comment } from './comment.model';

export const CommentActions = createActionGroup({
  source: 'Comment/API',
  events: {
    'Load Comments': props<{ comments: Comment[] }>(),
    'Add Comment': props<{ comment: Comment }>(),
    'Upsert Comments': props<{ comments: Comment[] }>(),
    'Update Comment': props<{ comment: Update<Comment> }>(),
    'Delete Comment': props<{ id: string }>(),
    'Clear Comments': emptyProps(),
  }
});
