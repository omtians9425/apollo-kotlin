package com.apollographql.apollo3.runtime.java;

import com.apollographql.apollo3.api.ApolloResponse;
import com.apollographql.apollo3.api.Operation;
import org.jetbrains.annotations.NotNull;

public interface ApolloCallback<D extends Operation.Data> {
  /**
   * Gets called when a response is available.
   * <p>
   * If there was a network error, {@link ApolloResponse#exception} will be non-null.
   *
   * @param response the response.
   */
  public abstract void onResponse(@NotNull ApolloResponse<D> response);
}
