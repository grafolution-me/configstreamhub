import { isDevMode } from '@angular/core';
import {
  ActionReducer,
  ActionReducerMap,
  createFeatureSelector,
  createSelector,
  MetaReducer, State
} from '@ngrx/store';
import {commentReducer, CommentState} from "./comment/comment.reducer";

export const appFeatureKey = 'app';

export interface MainStore {
  comments: CommentState;

}

export const reducers: ActionReducerMap<State> = {
  comments: commentReducer
};


export const metaReducers: MetaReducer<State>[] = isDevMode() ? [] : [];
