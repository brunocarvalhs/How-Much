package br.com.brunocarvalhs.howmuch.feature.ai_agent.data.service;

import br.com.brunocarvalhs.howmuch.core.ai.contract.AiSession;
import br.com.brunocarvalhs.howmuch.core.ai.registry.AgentRegistry;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class AiAgentFactoryImpl_Factory implements Factory<AiAgentFactoryImpl> {
  private final Provider<AiSession> sessionProvider;

  private final Provider<AgentRegistry> registryProvider;

  private AiAgentFactoryImpl_Factory(Provider<AiSession> sessionProvider,
      Provider<AgentRegistry> registryProvider) {
    this.sessionProvider = sessionProvider;
    this.registryProvider = registryProvider;
  }

  @Override
  public AiAgentFactoryImpl get() {
    return newInstance(sessionProvider.get(), registryProvider.get());
  }

  public static AiAgentFactoryImpl_Factory create(Provider<AiSession> sessionProvider,
      Provider<AgentRegistry> registryProvider) {
    return new AiAgentFactoryImpl_Factory(sessionProvider, registryProvider);
  }

  public static AiAgentFactoryImpl newInstance(AiSession session, AgentRegistry registry) {
    return new AiAgentFactoryImpl(session, registry);
  }
}
