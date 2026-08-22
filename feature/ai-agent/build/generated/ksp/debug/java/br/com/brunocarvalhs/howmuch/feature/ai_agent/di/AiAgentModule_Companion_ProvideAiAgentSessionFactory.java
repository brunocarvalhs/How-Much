package br.com.brunocarvalhs.howmuch.feature.ai_agent.di;

import br.com.brunocarvalhs.howmuch.core.domain.services.AuthService;
import br.com.brunocarvalhs.howmuch.feature.ai_agent.domain.entity.AiAgentSession;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class AiAgentModule_Companion_ProvideAiAgentSessionFactory implements Factory<AiAgentSession> {
  private final Provider<AuthService> authServiceProvider;

  private AiAgentModule_Companion_ProvideAiAgentSessionFactory(
      Provider<AuthService> authServiceProvider) {
    this.authServiceProvider = authServiceProvider;
  }

  @Override
  public AiAgentSession get() {
    return provideAiAgentSession(authServiceProvider.get());
  }

  public static AiAgentModule_Companion_ProvideAiAgentSessionFactory create(
      Provider<AuthService> authServiceProvider) {
    return new AiAgentModule_Companion_ProvideAiAgentSessionFactory(authServiceProvider);
  }

  public static AiAgentSession provideAiAgentSession(AuthService authService) {
    return Preconditions.checkNotNullFromProvides(AiAgentModule.Companion.provideAiAgentSession(authService));
  }
}
