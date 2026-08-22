package br.com.brunocarvalhs.howmuch.feature.ai_agent;

import br.com.brunocarvalhs.howmuch.core.ai.contract.AiAgentFactory;
import br.com.brunocarvalhs.howmuch.feature.ai_agent.domain.entity.AiAgentSession;
import br.com.brunocarvalhs.howmuch.feature.settings.app.domain.usecase.GetSettingsUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class AiAgentOrchestrator_Factory implements Factory<AiAgentOrchestrator> {
  private final Provider<AiAgentFactory> factoryProvider;

  private final Provider<GetSettingsUseCase> getSettingsUseCaseProvider;

  private final Provider<AiAgentSession> sessionProvider;

  private AiAgentOrchestrator_Factory(Provider<AiAgentFactory> factoryProvider,
      Provider<GetSettingsUseCase> getSettingsUseCaseProvider,
      Provider<AiAgentSession> sessionProvider) {
    this.factoryProvider = factoryProvider;
    this.getSettingsUseCaseProvider = getSettingsUseCaseProvider;
    this.sessionProvider = sessionProvider;
  }

  @Override
  public AiAgentOrchestrator get() {
    return newInstance(factoryProvider.get(), getSettingsUseCaseProvider.get(), sessionProvider.get());
  }

  public static AiAgentOrchestrator_Factory create(Provider<AiAgentFactory> factoryProvider,
      Provider<GetSettingsUseCase> getSettingsUseCaseProvider,
      Provider<AiAgentSession> sessionProvider) {
    return new AiAgentOrchestrator_Factory(factoryProvider, getSettingsUseCaseProvider, sessionProvider);
  }

  public static AiAgentOrchestrator newInstance(AiAgentFactory factory,
      GetSettingsUseCase getSettingsUseCase, AiAgentSession session) {
    return new AiAgentOrchestrator(factory, getSettingsUseCase, session);
  }
}
