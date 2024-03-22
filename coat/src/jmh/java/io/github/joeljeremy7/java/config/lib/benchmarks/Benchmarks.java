package io.github.joeljeremy7.java.config.lib.benchmarks;

import de.poiu.coat.validation.ConfigValidationException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.concurrent.TimeUnit;

public abstract class Benchmarks {

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        private Config     config;
        private MqttConfig mqttConfig;

        @Setup
        public void setup() throws IOException, URISyntaxException, ConfigValidationException {
          final Properties props= new Properties();
          props.load(Benchmark.class.getResourceAsStream("/AppProps.properties"));
          this.config           = ConfigBuilder.from(props);
          this.mqttConfig       = this.config.mqtt();
        }
    }

    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public static class Avgt extends Benchmarks {}

    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public static class Thrpt extends Benchmarks {}

    @Benchmark
    public String Coat_String(BenchmarkState state) {
        return state.config.test1();
    }

    @Benchmark
    public int Coat_Int(BenchmarkState state) {
        return state.config.testInt1();
    }

    @Benchmark
    public List<LocalDate> Coat_ListOfDates(BenchmarkState state) {
        return state.config.listOfDates();
    }

    @Benchmark
    public MqttConfig Coat_Embedded(BenchmarkState state) {
        return state.config.mqtt();
    }

    @Benchmark
    public InetAddress Coat_EmbeddedInetAddress(BenchmarkState state) {
        return state.config.mqtt().hostName();
    }

    @Benchmark
    public InetAddress Coat_InetAddress(BenchmarkState state) {
        return state.mqttConfig.hostName();
    }
}
